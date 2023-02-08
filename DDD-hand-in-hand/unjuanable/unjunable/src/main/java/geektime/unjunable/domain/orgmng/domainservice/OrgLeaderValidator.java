package geektime.unjunable.domain.orgmng.domainservice;

import geektime.unjunable.adapter.driving.persistence.orgmng.EmpRepositoryJdbc;
import geektime.unjunable.domain.common.exception.BusinessException;
import geektime.unjunable.domain.orgmng.entity.EmpStatus;
import org.springframework.stereotype.Component;

@Component
public class OrgLeaderValidator {

    private EmpRepositoryJdbc empRepository;

    public OrgLeaderValidator(EmpRepositoryJdbc empRepository) {
        this.empRepository = empRepository;
    }

    public void verify(Long tenant, Long leader) {
        if (leader != null
                && !empRepository.existsByIdAndStatus(tenant, leader, EmpStatus.REGULAR, EmpStatus.PROBATION)) {
            throw new BusinessException("组织负责人(id='"
                    + leader + "')不是在职员工！");
        }
    }
}
