package geektime.unjunable.domain.orgmng.domainservice;

import geektime.unjunable.domain.common.exception.BusinessException;
import geektime.unjunable.domain.orgmng.entity.EmpStatus;
import geektime.unjunable.domain.orgmng.entity.Org;
import geektime.unjunable.domain.orgmng.entity.OrgStatus;
import geektime.unjunable.domain.orgmng.repository.EmpRepository;
import geektime.unjunable.domain.orgmng.repository.OrgRepository;
import org.springframework.stereotype.Component;

@Component
public class CancelOrgValidator {

    private OrgRepository orgRepository;
    private EmpRepository empRepository;

    public CancelOrgValidator(OrgRepository orgRepository, EmpRepository empRepository) {
        this.orgRepository = orgRepository;
        this.empRepository = empRepository;
    }

    public void cancelledOrgShouldNotHasEmp(Long tenantId, Long id) {

        if (empRepository.findByOrgIdAndStatus(tenantId, id, EmpStatus.REGULAR).count() > 0) {
            throw new BusinessException("只有没有员工的组织才能被取消");
        }


    }

    public void OnlyEffectiveOrgCanBeCancelled(Org org) {
        if (org.getStatus() != OrgStatus.EFFECTIVE) {
            throw new BusinessException("只有有效的组织才能被取消");
        }
    }
}
