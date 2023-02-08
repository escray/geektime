package geektime.unjunable.application.orgmng;

import geektime.unjunable.adapter.driven.restful.orgmng.OrgDto;
import geektime.unjunable.adapter.driving.persistence.orgmng.OrgRepositoryJdbc;
import geektime.unjunable.domain.orgmng.entity.Org;
import geektime.unjunable.domain.orgmng.factory.OrgBuilder;
import geektime.unjunable.domain.orgmng.factory.OrgBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgService {

    //代替了原来多个 Repository
    //现在只需要注入工厂和仓库了

    private final OrgBuilderFactory orgBuilderFactory;
    private final OrgRepositoryJdbc orgRepository;

    @Autowired
    public OrgService(OrgBuilderFactory orgBuilderFactory, OrgRepositoryJdbc orgRepository) {

        this.orgBuilderFactory = orgBuilderFactory;
        this.orgRepository = orgRepository;
    }

    // "添加组织"功能的入口
    public OrgDto addOrg(OrgDto request, Long userId) {
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

    // 将领域对象的值赋给DTO...
    private OrgDto buildOrgDto(Org org) {
        return null;
    }

    //原来DTO转领域对象的逻辑也移到了工厂
}