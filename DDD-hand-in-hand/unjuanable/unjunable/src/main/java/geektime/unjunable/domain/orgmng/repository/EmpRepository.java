package geektime.unjunable.domain.orgmng.repository;

import geektime.unjunable.domain.orgmng.entity.Emp;
import geektime.unjunable.domain.orgmng.entity.EmpStatus;

import java.util.stream.Stream;

public interface EmpRepository {
    boolean existsByIdAndStatus(Long tenant, Long leader, EmpStatus regular, EmpStatus probation);

    Stream<Emp> findByOrgIdAndStatus(Long tenantId, Long id, EmpStatus regular);
}
