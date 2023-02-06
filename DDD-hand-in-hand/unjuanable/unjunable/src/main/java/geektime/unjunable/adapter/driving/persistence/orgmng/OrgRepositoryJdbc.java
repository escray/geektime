//注意：Repository 的实现类在适配器层
package geektime.unjunable.adapter.driving.persistence.orgmng;

import geektime.unjunable.domain.orgmng.Org;
import geektime.unjunable.domain.orgmng.OrgRepository;
import geektime.unjunable.domain.orgmng.OrgStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrgRepositoryJdbc implements OrgRepository {
    JdbcTemplate jdbc;
    SimpleJdbcInsert insertOrg;

    @Autowired
    public OrgRepositoryJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<Org> findByIdAndStatus(Long tenantId, Long id, OrgStatus status) {
        return Optional.empty();
    }

    @Override
    public int countBySuperiorAndName(Long tenantId, Long superiorID, String name) {
        return 0;
    }

    @Override
    public boolean existsBySuperiorAndName(Long tenant, Long superior, String name) {
        return false;
    }

    @Override
    public Org save(Org org) {
        return null;
    }
}
