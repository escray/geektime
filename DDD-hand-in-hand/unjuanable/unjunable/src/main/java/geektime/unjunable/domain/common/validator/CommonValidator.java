package geektime.unjunable.domain.common.validator;

import geektime.unjunable.adapter.driving.persistence.tenantmng.TenantRepositoryJdbc;
import geektime.unjunable.common.framework.BusinessException;
import geektime.unjunable.domain.tenantmng.TenantStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonValidator {
    private final TenantRepositoryJdbc tenantRepository;

    @Autowired
    public CommonValidator(TenantRepositoryJdbc tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public void tenantShouldValid(Long tenant) {
        if (!tenantRepository.existsByIdAndStatus(tenant, TenantStatus.EFFECTIVE)) {
            throw new BusinessException("id 为" + tenant + "'的租户不是有效租户'");
        }
    }
}
