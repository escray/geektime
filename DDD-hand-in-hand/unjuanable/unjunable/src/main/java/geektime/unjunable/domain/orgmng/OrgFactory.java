package geektime.unjunable.domain.orgmng;

import geektime.unjunable.adapter.driven.restful.orgmng.OrgDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrgFactory {
    private final OrgValidator validator;

    @Autowired
    public OrgFactory(OrgValidator validator) {
        this.validator = validator;
    }

    public Org build(OrgDto request, Long userId) {
        validator.validate(request);
        return buildOrg(request, userId);
    }

    //DTO转换成领域对象
    private Org buildOrg(OrgDto request, Long userId) {
        return null;
    }
}
