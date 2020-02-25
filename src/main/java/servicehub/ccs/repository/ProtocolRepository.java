package servicehub.ccs.repository;

import org.springframework.stereotype.Repository;
import servicehub.ccs.domain.Protocol;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProtocolRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Protocol protocol) {
        em.persist(protocol);
    }
}
