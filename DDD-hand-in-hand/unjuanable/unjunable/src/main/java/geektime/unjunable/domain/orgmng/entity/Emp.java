package geektime.unjunable.domain.orgmng.entity;

import geektime.unjunable.common.framework.domain.AuditableEntity;

import java.time.LocalDateTime;

public class Emp extends AuditableEntity {
    public Emp(LocalDateTime createdAt, Long createdBy) {
        super(createdAt, createdBy);
    }
}
