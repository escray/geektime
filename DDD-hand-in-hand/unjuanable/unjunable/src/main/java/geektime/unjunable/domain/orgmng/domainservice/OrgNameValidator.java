package geektime.unjunable.domain.orgmng.domainservice;

import geektime.unjunable.domain.common.exception.BusinessException;
import geektime.unjunable.domain.orgmng.repository.OrgRepository;
import org.springframework.stereotype.Component;

@Component
public class OrgNameValidator {
    private OrgRepository orgRepository;

    public OrgNameValidator(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }

    public void verify(Long tenant, String name, Long superior) {
        // 组织必须有名称
        orgNameShouldNotEmpty(name);
        // 同一个组织下的下级组织不能重名
        orgNameShouldNotDuplicatedInSameSuperior(tenant, superior, name);
    }

    private void orgNameShouldNotDuplicatedInSameSuperior(Long tenant, Long superior, String orgName) {
        if (orgRepository.existsBySuperiorAndName(tenant, superior, orgName)) {
            throw new BusinessException("同一上级下已经有名为'"
                    + orgName + "'的组织存在！");
        }
    }

    private void orgNameShouldNotEmpty(String orgName) {
        if (orgName.isBlank()) {
            throw new BusinessException("组织没有名称！");
        }
    }
}
