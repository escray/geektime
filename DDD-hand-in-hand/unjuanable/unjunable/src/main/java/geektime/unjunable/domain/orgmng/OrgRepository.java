package geektime.unjunable.domain.orgmng;

import java.util.Optional;

public interface OrgRepository {
    Optional<Org> findByIdAndStatus(Long tenantId, Long id, OrgStatus status);

    int countBySuperiorAndName(Long tenantId, Long superiorID, String name);

    boolean existsBySuperiorAndName(Long tenant, Long superior, String name);

    Org save(Org org);
}
