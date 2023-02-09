package geektime.unjunable.adapter.driven.restful.orgmng;

import geektime.unjunable.application.orgmng.orgservice.OrgResponse;
import geektime.unjunable.application.orgmng.orgservice.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrgController {
    private final OrgService orgService;

    @Autowired
    public OrgController(OrgService orgService) {
        this.orgService = orgService;
    }

    @PostMapping("/api/organizations")
    public OrgResponse addOrg(@RequestBody OrgResponse request) {
        //从请求里解析出 userId ...
        Long userId = 1L;
        return orgService.addOrg(request, userId);
    }
}
