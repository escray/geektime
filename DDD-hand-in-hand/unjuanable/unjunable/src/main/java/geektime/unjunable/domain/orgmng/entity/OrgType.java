package geektime.unjunable.domain.orgmng.entity;

public enum OrgType {
    ENTERPRISE("ENTP"),
    DEVELOPCENTER("DEVCENT"),
    DEVELOPGROUP("DEVGRP"),
    OTHER("OTHER");

    private final String text;

    OrgType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public String getCode() {
        return text;
    }
}
