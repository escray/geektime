package geektime.unjunable.domain.orgmng;

import geektime.unjunable.domain.common.validator.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrgBuilderFactory {
    private final CommonValidator commonValidator;
    private final OrgTypeValidator orgTypeValidator;
    private final SuperiorValidator superiorValidator;
    private final OrgNameValidator orgNameValidator;
    private final OrgLeaderValidator orgLeaderValidator;

    @Autowired
    public OrgBuilderFactory(CommonValidator commonValidator
            , OrgTypeValidator orgTypeValidator
            , SuperiorValidator superiorValidator
            , OrgNameValidator orgNameValidator
            , OrgLeaderValidator orgLeaderValidator) {
        this.commonValidator = commonValidator;
        this.orgTypeValidator = orgTypeValidator;
        this.superiorValidator = superiorValidator;
        this.orgNameValidator = orgNameValidator;
        this.orgLeaderValidator = orgLeaderValidator;
    }

    //每次调用都创建一个新的 OrgBuilder
    public OrgBuilder create() {
        return new OrgBuilder(commonValidator
                , orgTypeValidator
                , superiorValidator
                , orgNameValidator
                , orgLeaderValidator);
    }
}
