package geektime.unjunable.adapter.driving.persistence.orgmng;

import geektime.unjunable.domain.orgmng.entity.Emp;
import geektime.unjunable.domain.orgmng.entity.EmpStatus;
import geektime.unjunable.domain.orgmng.repository.EmpRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public class EmpRepositoryJdbc implements EmpRepository {
    public boolean existsByIdAndStatus(Long tenant, Long leader, EmpStatus regular, EmpStatus probation) {
        return false;
    }

    @Override
    public Stream<Emp> findByOrgIdAndStatus(Long tenantId, Long id, EmpStatus regular) {
        return null;
    }
}
