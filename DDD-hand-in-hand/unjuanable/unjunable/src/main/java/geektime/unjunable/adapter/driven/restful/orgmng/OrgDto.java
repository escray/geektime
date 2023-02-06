package geektime.unjunable.adapter.driven.restful.orgmng;

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
}
