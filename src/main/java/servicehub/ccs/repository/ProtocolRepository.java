package servicehub.ccs.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProtocolRepository {
    @PersistenceContext
    private EntityManager em;
}
