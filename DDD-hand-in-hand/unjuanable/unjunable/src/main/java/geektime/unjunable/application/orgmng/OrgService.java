package geektime.unjunable.application.orgmng;

import geektime.unjunable.adapter.driven.restful.orgmng.OrgDto;
import geektime.unjunable.adapter.driving.persistence.orgmng.EmpRepository;
import geektime.unjunable.adapter.driving.persistence.orgmng.OrgRepository;
import geektime.unjunable.adapter.driving.persistence.orgmng.OrgTypeRepository;
import geektime.unjunable.adapter.driving.persistence.tenantmng.TenantRepository;
import geektime.unjunable.domain.orgmng.Org;

public class OrgService {
//    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final OrgTypeRepository orgTypeRepository;
    private final OrgRepository orgRepository;
    private final EmpRepository empReposiroty;

    public OrgService(TenantRepository tenantRepository,
                      OrgTypeRepository orgTypeRepository,
                      OrgRepository orgRepository,
                      EmpRepository empRepository) {
        this.tenantRepository = tenantRepository;
        this.orgTypeRepository = orgTypeRepository;
        this.orgRepository = orgRepository;
        this.empReposiroty = empRepository;
    }

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


    //进行各种业务规则的校验，会用到上面的各个Repository...
    private void validate(OrgDto request, Long userId) {
    }
}
