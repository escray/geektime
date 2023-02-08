package geektime.unjunable.domain.orgmng.domainservice;

import geektime.unjunable.adapter.driving.persistence.orgmng.OrgTypeRepositoryJdbc;
import geektime.unjunable.domain.common.exception.BusinessException;
import geektime.unjunable.domain.common.exception.DirtyDataException;
import geektime.unjunable.domain.orgmng.entity.OrgTypeStatus;
import geektime.unjunable.domain.orgmng.entity.Org;
import geektime.unjunable.domain.orgmng.entity.OrgStatus;
import geektime.unjunable.domain.orgmng.entity.OrgType;
import geektime.unjunable.domain.orgmng.repository.OrgRepository;
import org.springframework.stereotype.Component;

@Component
public class SuperiorValidator {
    private OrgTypeRepositoryJdbc orgTypeRepository;
    private OrgRepository orgRepository;

    public SuperiorValidator(OrgTypeRepositoryJdbc orgTypeRepository, OrgRepository orgRepository) {
        this.orgTypeRepository = orgTypeRepository;
        this.orgRepository = orgRepository;
    }

    public void verify(Long tenant, Long superior, String orgType) {
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
}
