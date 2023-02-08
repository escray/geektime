package geektime.unjunable.domain.orgmng.domainservice;

import geektime.unjunable.adapter.driving.persistence.orgmng.OrgTypeRepositoryJdbc;
import geektime.unjunable.domain.common.exception.BusinessException;
import geektime.unjunable.domain.orgmng.entity.OrgTypeStatus;
import org.springframework.stereotype.Component;

@Component
public class OrgTypeValidator {
    private OrgTypeRepositoryJdbc orgTypeRepository;

    public OrgTypeValidator(OrgTypeRepositoryJdbc orgTypeRepository) {
        this.orgTypeRepository = orgTypeRepository;
    }

    public void verify(Long tenant, String orgType) {
        // 组织类别不能为空
        orgTypeShouldNotEmpty(orgType);
        // 组织类别必须有效
        orgTypeShouldBeValid(tenant, orgType);
        // 企业是在创建租户的时候创建好的，因此不能单独创建企业
        shouldNotCreateEntpAlone(orgType);
    }

    private void orgTypeShouldBeValid(Long tenant, String orgType) {
        if (!orgTypeRepository.existsByCodeAndStatus(tenant, orgType, OrgTypeStatus.EFFECTIVE)) {
            throw new BusinessException("'" + orgType + "'不是有效的组织类别代码！");
        }
    }

    private void shouldNotCreateEntpAlone(String orgType) {
        if ("ENTP".equals(orgType)) {
            throw new BusinessException("企业是在创建租户的时候创建好的，因此不能单独创建企业!");
        }
    }

    private void orgTypeShouldNotEmpty(String orgType) {
        if (orgType.isBlank()) {
            throw new BusinessException("组织类别不能为空");
        }
    }
}
