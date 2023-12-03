package programmingmagic.domain.technology;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import programmingmagic.base.BaseRepository;

@Dependent
public class TechnologyRepository extends BaseRepository<Technology> {
    public TechnologyRepository() {
        super(Technology.class);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}


