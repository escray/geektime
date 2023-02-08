package geektime.unjunable.adapter.driven.restful.orgmng;

import geektime.unjunable.domain.orgmng.OrgType;
import geektime.unjunable.domain.tenantmng.Tenant;

import java.time.LocalDateTime;

public class OrgDto {
    private Long id;
    private Long tenantId;
    private Long superiorId;
    private String orgTypeCode;
    private Long leaderID;
    private String name;
    private String status;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime lastUpdatedAt;
    private Long lastUpdatedBy;

    public Long getTenant() {
        return 1L;
    }

    public String getOrgType() {
        return orgTypeCode;
    }

    public Long getSuperior() {
        return superiorId;
    }

    public Long getLeader() {
        return leaderID;
    }

    public String getName() {
        return name;
    }
}
