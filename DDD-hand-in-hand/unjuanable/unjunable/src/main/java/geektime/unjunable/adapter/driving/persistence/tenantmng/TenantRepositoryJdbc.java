package geektime.unjunable.adapter.driving.persistence.tenantmng;

import geektime.unjunable.domain.tenantmng.Tenant;
import geektime.unjunable.domain.tenantmng.TenantStatus;
import org.springframework.stereotype.Repository;

@Repository
public class TenantRepositoryJdbc {
    public boolean existsByIdAndStatus(Long tenant, TenantStatus effective) {
        return false;
    }
}
