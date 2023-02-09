package geektime.unjunable.domain.orgmng.entity;

import geektime.unjunable.common.framework.domain.AuditableEntity;

import java.time.LocalDateTime;

public class Org extends AuditableEntity {
    private Long id;
    private Long tenantId;
    private Long superiorId;
    private String orgTypeCode;
    private Long leaderId;
    private String name;
    private OrgStatus status;

    public Org(Long tenantId, String orgTypeCode
            , LocalDateTime createdAt, Long createdBy) {
        super(createdAt, createdBy);
        this.tenantId = tenantId;
        this.orgTypeCode = orgTypeCode;

        //组织的初始状态默认为有效
        this.status = OrgStatus.EFFECTIVE;
    }

    // 所有属性的 getter ...
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public Long getSuperiorId() {
        return superiorId;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public OrgType getOrgType() {
        return OrgType.ENTERPRISE;
    }

    public OrgStatus getStatus() {
        return this.status;
    }

    public void setSuperiorId(Long superiorId) {
        this.superiorId = superiorId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Org 自己管理自己的状态
    public void cancel() {
        this.status = OrgStatus.CANCELLED;
    }

    public boolean isEffective() {
        return status.equals(OrgStatus.EFFECTIVE);
    }
}