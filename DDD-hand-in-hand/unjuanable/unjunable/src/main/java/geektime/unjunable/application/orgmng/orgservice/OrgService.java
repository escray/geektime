package geektime.unjunable.application.orgmng.orgservice;

import geektime.unjunable.adapter.driving.persistence.orgmng.OrgRepositoryJdbc;
import geektime.unjunable.domain.orgmng.org.OrgHandler;
import geektime.unjunable.domain.common.exception.BusinessException;
import geektime.unjunable.domain.orgmng.entity.Org;
import geektime.unjunable.domain.orgmng.factory.OrgBuilder;
import geektime.unjunable.domain.orgmng.factory.OrgBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrgService {

    //代替了原来多个 Repository
    //现在只需要注入工厂和仓库了
    private final OrgBuilderFactory orgBuilderFactory;
    private final OrgRepositoryJdbc orgRepository;
    //新增了一个领域服务依赖
    private final OrgHandler orgHandler;


    @Autowired
    public OrgService(OrgBuilderFactory orgBuilderFactory, OrgRepositoryJdbc orgRepository, OrgHandler orgHandler) {

        this.orgBuilderFactory = orgBuilderFactory;
        this.orgRepository = orgRepository;
        this.orgHandler = orgHandler;
    }

    // "添加组织"功能的入口
    @Transactional
    public OrgResponse addOrg(OrgResponse request, Long userId) {
        OrgBuilder builder = orgBuilderFactory.create();
        Org org = builder.tenantId(request.getTenantId())
                .orgTypeCode(request.getOrgTypeCode())
                .leaderId(request.getLeaderId())
                .superiorId(request.getSuperiorId())
                .name(request.getName())
                .createdBy(userId)
                .build();

        org = orgRepository.save(org);
        return buildOrgDto(org);
    }

    //修改组织基本信息
    @Transactional
    public OrgResponse updateOrgBasic(Long id, OrgResponse request, Long userId) {
        Org org = orgRepository.findById(request.getTenantId(), id)
                .orElseThrow(() -> {
                        throw new BusinessException("要修改的组织(id ="
                                + id + "  )不存在！");
                });

        orgHandler.updateBasic(org, request.getName(), request.getLeaderId(), userId);

        orgRepository.update(org);

        return buildOrgDto(org);
    }

    //取消组织
    @Transactional
    public Long cancelOrg(Long id, Long tenantId, Long userId) {
        Org org = orgRepository.findById(tenantId, id)
                .orElseThrow(() -> {
                    throw new BusinessException("要取消的组织(id ="
                            + id + "  )不存在！");
                });
        orgHandler.cancel(org, userId);
        orgRepository.update(org);

        return org.getId();
    }

    // 将领域对象的值赋给DTO...
    private OrgResponse buildOrgDto(Org org) {
        return null;
    }

    //原来DTO转领域对象的逻辑也移到了工厂
}