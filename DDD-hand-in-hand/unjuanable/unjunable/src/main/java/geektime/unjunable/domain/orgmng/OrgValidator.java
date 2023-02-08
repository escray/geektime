package geektime.unjunable.domain.orgmng;

import geektime.unjunable.adapter.driven.restful.orgmng.OrgDto;
import geektime.unjunable.domain.common.validator.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrgValidator {
    //依赖更小的 Validator，也就是更小的规则分组
    private final CommonValidator commonValidator;
    private final OrgTypeValidator orgTypeValidator;
    private final SuperiorValidator superiorValidator;
    private final OrgNameValidator orgNameValidator;
    private final OrgLeaderValidator orgLeaderValidator;

    @Autowired
    public OrgValidator(CommonValidator commonValidator, OrgTypeValidator orgTypeValidator, SuperiorValidator superiorValidator, OrgNameValidator orgNameValidator, OrgLeaderValidator orgLeaderValidator) {
        this.commonValidator = commonValidator;
        this.orgTypeValidator = orgTypeValidator;
        this.superiorValidator = superiorValidator;
        this.orgNameValidator = orgNameValidator;
        this.orgLeaderValidator = orgLeaderValidator;
    }

    public void validate(OrgDto request) {
        final Long tenant = request.getTenant();

        commonValidator.tenantShouldValid(tenant);
        orgLeaderValidator.verify(tenant, request.getLeader());
        orgTypeValidator.verify(tenant, request.getOrgType());
        superiorValidator.verify(tenant, request.getSuperior(), request.getOrgType());
        orgNameValidator.verify(tenant, request.getName(), request.getSuperior());
    }
}
