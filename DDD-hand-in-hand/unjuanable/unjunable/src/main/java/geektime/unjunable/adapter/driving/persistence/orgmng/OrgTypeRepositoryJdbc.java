package geektime.unjunable.adapter.driving.persistence.orgmng;

import geektime.unjunable.domain.orgmng.entity.OrgType;
import geektime.unjunable.domain.orgmng.entity.OrgTypeStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrgTypeRepositoryJdbc {
    public boolean existsByCodeAndStatus(Long tenant, String orgType, OrgTypeStatus effective) {
        return false;
    }

    public Optional<OrgType> findByCodeAndStatus(Long tenant, OrgType orgType, OrgTypeStatus status) {
        return Optional.empty();
    }
}
