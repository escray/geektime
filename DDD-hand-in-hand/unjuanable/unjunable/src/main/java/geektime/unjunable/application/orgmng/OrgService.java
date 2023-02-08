package geektime.unjunable.application.orgmng;

import geektime.unjunable.adapter.driven.restful.orgmng.OrgDto;
import geektime.unjunable.adapter.driving.persistence.orgmng.EmpRepositoryJdbc;
import geektime.unjunable.adapter.driving.persistence.orgmng.OrgRepositoryJdbc;
import geektime.unjunable.adapter.driving.persistence.orgmng.OrgTypeRepositoryJdbc;
import geektime.unjunable.adapter.driving.persistence.tenantmng.TenantRepositoryJdbc;
import geektime.unjunable.adapter.driving.persistence.usermng.UserRepositoryJdbc;
import geektime.unjunable.common.framework.BusinessException;
import geektime.unjunable.common.framework.DirtyDataException;
import geektime.unjunable.domain.orgmng.*;
import geektime.unjunable.domain.tenantmng.TenantStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgService {
    private final UserRepositoryJdbc userRepository;
    private final TenantRepositoryJdbc tenantRepository;
    private final OrgTypeRepositoryJdbc orgTypeRepository;
    private final OrgRepositoryJdbc orgRepository;
    private final EmpRepositoryJdbc empRepository;

    @Autowired
    public OrgService(UserRepositoryJdbc userRepository,
                      TenantRepositoryJdbc tenantRepository,
                      OrgTypeRepositoryJdbc orgTypeRepository,
                      OrgRepositoryJdbc orgRepository,
                      EmpRepositoryJdbc empRepository) {
        this.userRepository = userRepository;
        this.tenantRepository = tenantRepository;
        this.orgTypeRepository = orgTypeRepository;
        this.orgRepository = orgRepository;
        this.empRepository = empRepository;
    }

    // "添加组织"功能的入口
    public OrgDto addOrg(OrgDto request, Long userId) {
        validate(request, userId);
        Org org = buildOrg(request, userId);
        org = orgRepository.save(org);
        return buildOrgDto(org);
    }

    // 将领域对象的值赋给DTO...
    private OrgDto buildOrgDto(Org org) {
        return null;
    }

    // 将DTO的值赋给领域对象...
    private Org buildOrg(OrgDto request, Long userId) {
        return null;
    }


    //主要的领域逻辑在这个方法
    //进行各种业务规则的校验，会用到上面的各个Repository...
    private void validate(OrgDto request, Long userId) {
        final Long tenant = request.getTenant();

        //把各业务规则抽成了方法

        // 租户必须有效
        tenantShouldBeValid(tenant);

        // 组织负责人可以空缺，如果有的话，的必须是一个在职员工（含试用期）
        leaderShouldBeEffective(tenant, request.getLeader());

        // 为了避免这个方法太长，把一些规则进一步分了组

        // 校验组织类别的规则分组
        verifyOrgType(tenant, request.getOrgType());

        // 校验上级组织的规则分组
        validateSuperior(tenant, request.getOrgType(), request.getSuperior());

        // 校验组织名称的规则分组
        verifyOrgName(tenant, request.getSuperior(), request.getName());
    }

    private void verifyOrgName(Long tenant, Long superior, String orgName) {
        // 组织必须有名称
        orgNameShouldNotEmpty(orgName);
        // 同一个组织下的下级组织不能重名
        orgNameShouldNotDuplicatedInSameSuperior(tenant, superior, orgName);
    }

    private void orgNameShouldNotDuplicatedInSameSuperior(Long tenant, Long superior, String orgName) {
        if (orgRepository.existsBySuperiorAndName(tenant, superior, orgName)) {
            throw new BusinessException("同一上级下已经有名为'"
                    + orgName + "'的组织存在！");
        }
    }

    private void orgNameShouldNotEmpty(String orgName) {
        if (isBlank(orgName)) {
            throw new BusinessException("组织没有名称！");
        }
    }

    private void validateSuperior(Long tenant, String orgType, Long superior) {
        // 上级组织应该是有效组织
        Org superiorOrg = superiorShouldEffective(tenant, superior);
        // 取上级组织的组织类别
        OrgType superiorOrgType = findSuperiorOrgType(tenant, superior, superiorOrg);
        // 开发组的上级只能是开发中心
        superiorOfDevGroupMustBeDevCenter(orgType, superior, superiorOrgType);
        // 开发中心和直属部门的上级只能是企业
        superiorOfDevCenterAndDirectDeptMustBeEntp(orgType, superior, superiorOrgType);
    }

    private void superiorOfDevCenterAndDirectDeptMustBeEntp(String orgType, Long superior, OrgType superiorOrgType) {
        if (("DEVCENT".equals(orgType) || "DIRDEP".equals(orgType))
                && !"ENTP".equals(superiorOrgType.getCode())) {
            throw new BusinessException("开发中心或直属部门的上级(id = '"
                    + superior + "')不是企业！");
        }
    }

    private void superiorOfDevGroupMustBeDevCenter(String orgType, Long superior, OrgType superiorOrgType) {
        if ("DEVGRP".equals(orgType) && !"DEVCENT".equals(superiorOrgType.getCode())) {
            throw new BusinessException("开发组的上级 （id = '"
                    + superior + "'）不是开发中心！");
        }
    }

    private OrgType findSuperiorOrgType(Long tenant, Long superior, Org superiorOrg) {
        OrgType superiorOrgType = orgTypeRepository.findByCodeAndStatus(tenant,
                        superiorOrg.getOrgType(),
                        OrgTypeStatus.EFFECTIVE)
                .orElseThrow(() -> new DirtyDataException("id 为 '"
                        + superior
                        + "' 的组织的组织类型代码 '"
                        + superiorOrg.getOrgType()
                        + "' 无效！"));
        return superiorOrgType;
    }

    private Org superiorShouldEffective(Long tenant, Long superior) {
        Org superiorOrg = orgRepository.findByIdAndStatus(tenant, superior, OrgStatus.EFFECTIVE)
                .orElseThrow(() -> new BusinessException("'" + superior
                        + "' 不是有效的组织 id !"));
        return superiorOrg;
    }

    private void leaderShouldBeEffective(Long tenant, Long leader) {
        if (leader != null
                && !empRepository.existsByIdAndStatus(tenant, leader, EmpStatus.REGULAR, EmpStatus.PROBATION)) {
            throw new BusinessException("组织负责人(id='"
                    + leader + "')不是在职员工！");
        }
    }

    private void verifyOrgType(Long tenant, String orgType) {
        // 组织类别不能为空
        orgTypeShouldNotEmpty(orgType);
        // 组织类别必须有效
        orgTypeShouldBeValid(tenant, orgType);
        // 企业是在创建租户的时候创建好的，因此不能单独创建企业
        shouldNotCreateEntpAlone(orgType);
    }

    private void orgTypeShouldBeValid(Long tenant, String orgType) {
        if (!orgTypeRepository.existsByCodeAndStatus(tenant, orgType, OrgTypeStatus.EFFECTIVE)) {
            throw new BusinessException("'" + orgType + "'不是有效的组织类别代码！");
        }
    }

    private void shouldNotCreateEntpAlone(String orgType) {
        if ("ENTP".equals(orgType)) {
            throw new BusinessException("企业是在创建租户的时候创建好的，因此不能单独创建企业!");
        }
    }

    private void orgTypeShouldNotEmpty(String orgType) {
        if (isBlank(orgType)) {
            throw new BusinessException("组织类别不能为空");
        }
    }

    private void tenantShouldBeValid(Long tenant) {
        if (!tenantRepository.existsByIdAndStatus(tenant, TenantStatus.EFFECTIVE)) {
            throw new BusinessException("id 为" + tenant + "'的租户不是有效租户'");
        }
    }

    private boolean isBlank(String code) {
        return false;
    }
}
