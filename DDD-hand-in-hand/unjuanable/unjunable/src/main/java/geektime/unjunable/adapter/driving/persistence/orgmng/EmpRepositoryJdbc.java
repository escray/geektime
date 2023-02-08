package geektime.unjunable.adapter.driving.persistence.orgmng;

import geektime.unjunable.domain.orgmng.entity.EmpStatus;
import org.springframework.stereotype.Repository;

@Repository
public class EmpRepositoryJdbc {
    public boolean existsByIdAndStatus(Long tenant, Long leader, EmpStatus regular, EmpStatus probation) {
        return false;
    }
}
