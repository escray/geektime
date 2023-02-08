package geektime.unjunable.domain.orgmng;

import java.time.LocalDateTime;

public class Org {
    private Long id;
    private Long tenantId;
    private Long superiorId;
    private String orgTypeCode;
    private Long leaderId;
    private String name;
    private OrgStatus status;
    private LocalDateTime createAt;
    private Long createdBy;
    private LocalDateTime lastUpdatedAt;
    private Long lastUpdatedBy;

    public Org() {
        //组织的初始状态默认为有效
        this.status = OrgStatus.EFFECTIVE;
    }

    public OrgType getOrgType() {
        return OrgType.ENTERPRISE;
    }
}
