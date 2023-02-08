package geektime.unjunable.application.orgmng;

import geektime.unjunable.adapter.driven.restful.orgmng.OrgDto;
import geektime.unjunable.adapter.driving.persistence.orgmng.OrgRepositoryJdbc;
import geektime.unjunable.domain.orgmng.Org;
import geektime.unjunable.domain.orgmng.OrgValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgService {

    //代替了原来多个 Repository
    private final OrgValidator validator;
    private final OrgRepositoryJdbc orgRepository;

    @Autowired
    public OrgService(OrgValidator validator, OrgRepositoryJdbc orgRepository) {
        this.validator = validator;
        this.orgRepository = orgRepository;
    }

    // "添加组织"功能的入口
    public OrgDto addOrg(OrgDto request, Long userId) {
        // 代替原来的 validate() 方法调用
        validator.validate(request);
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
}