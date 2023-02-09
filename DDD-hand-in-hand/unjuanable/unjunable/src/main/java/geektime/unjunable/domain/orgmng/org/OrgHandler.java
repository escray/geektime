package geektime.unjunable.domain.orgmng.org;

import geektime.unjunable.domain.common.validator.CommonValidator;
import geektime.unjunable.domain.orgmng.domainservice.CancelOrgValidator;
import geektime.unjunable.domain.orgmng.domainservice.OrgLeaderValidator;
import geektime.unjunable.domain.orgmng.domainservice.OrgNameValidator;
import geektime.unjunable.domain.orgmng.entity.Org;
import geektime.unjunable.domain.orgmng.entity.OrgStatus;
import org.springframework.stereotype.Component;

@Component
public class OrgHandler {
    private final CommonValidator commonValidator;
    private final OrgNameValidator nameValidator;
    private final OrgLeaderValidator leaderValidator;
    private final CancelOrgValidator cancelValidator;

    public OrgHandler(CommonValidator commonValidator
            , OrgNameValidator nameValidator
            , OrgLeaderValidator leaderValidator
            , CancelOrgValidator cancelValidator) {
        this.commonValidator = commonValidator;
        this.nameValidator = nameValidator;
        this.leaderValidator = leaderValidator;
        this.cancelValidator = cancelValidator;
    }

    public void updateBasic(Org org, String name, Long leaderId, Long userId) {
        updateName(org, name);
        updateLeader(org, leaderId);
        updateAuditInfo(org, userId);
    }

    private void updateLeader(Org org, Long leaderId) {
        if (leaderId != null && !leaderId.equals(org.getLeaderId())) {
            leaderValidator.verify(org.getTenantId(), leaderId);
            org.setLeaderId(leaderId);
        }
    }

    private void updateName(Org org, String name) {
        if (name != null && !name.equals(org.getName())) {
            nameValidator.verify(org.getTenantId(), org.getName(), org.getSuperiorId());
            org.setName(name);
        }
    }

    public void cancel(Org org, Long userId) {
        cancelValidator.cancelledOrgShouldNotHasEmp(org.getTenantId(), org.getId());
        cancelValidator.OnlyEffectiveOrgCanBeCancelled(org);
        // 只需告诉 Org 要进行撤销，但不必了解 Org 内部细节
        org.cancel();
        updateAuditInfo(org, userId);
    }

    private void updateAuditInfo(Org org, Long userId) {
        // 设置最后修改人和时间
    }
}
