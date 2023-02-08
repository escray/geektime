package geektime.unjunable.application.orgmng;

import geektime.unjunable.adapter.driven.restful.orgmng.OrgDto;
import geektime.unjunable.adapter.driving.persistence.orgmng.OrgRepositoryJdbc;
import geektime.unjunable.domain.orgmng.Org;
import geektime.unjunable.domain.orgmng.OrgFactory;
import geektime.unjunable.domain.orgmng.OrgValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgService {

    //代替了原来多个 Repository
    //现在只需要注入工厂和仓库了
    private final OrgFactory orgFactory;
    private final OrgRepositoryJdbc orgRepository;

    @Autowired
    public OrgService(OrgFactory orgFactory, OrgRepositoryJdbc orgRepository) {
        this.orgFactory = orgFactory;
        this.orgRepository = orgRepository;
    }

    // "添加组织"功能的入口
    public OrgDto addOrg(OrgDto request, Long userId) {
        Org org = orgFactory.build(request, userId);
        org = orgRepository.save(org);
        return buildOrgDto(org);
    }

    // 将领域对象的值赋给DTO...
    private OrgDto buildOrgDto(Org org) {
        return null;
    }

    //原来DTO转领域对象的逻辑也移到了工厂
}