package geektime.unjunable.application.orgmng.orgservice;

public class OrgResponse {

    private Long leaderId;
    private String name;
    private String orgTypeCode;
    private Long superiorId;
    private Long tenantId;

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
