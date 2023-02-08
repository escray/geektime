package geektime.unjunable.adapter.driven.restful.orgmng;

import java.time.LocalDateTime;

public class OrgDto {
    private Long id;
    private Long tenantId;
    private Long superiorId;
    private String orgTypeCode;
    private Long leaderId;
    private String name;
    private String status;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime lastUpdatedAt;
    private Long lastUpdatedBy;


    public String getOrgType() {
        return orgTypeCode;
    }

    public String getName() {
        return name;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public String getOrgTypeCode() {
        return orgTypeCode;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public Long getSuperiorId() {
        return superiorId;
    }
}
