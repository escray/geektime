package geektime.unjunable.domain.orgmng.entity;

import java.time.LocalDateTime;

public class Org {
    private Long id;
    private Long tenantId;
    private Long superiorId;
    private String orgTypeCode;
    private Long leaderId;
    private String name;
    private OrgStatus status;
    private LocalDateTime createdAt;
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

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public void setSuperiorId(Long superiorId) {
        this.superiorId = superiorId;
    }

    public void setOrgTypeCode(String orgTypeCode) {
        this.orgTypeCode = orgTypeCode;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(LocalDateTime createAt) {
        this.createdAt = createAt;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
