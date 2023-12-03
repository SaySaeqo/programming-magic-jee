package programmingmagic.domain.programminglanguage;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import programmingmagic.base.BaseRepository;

@Dependent
public class ProgrammingLanguageRepository extends BaseRepository<ProgrammingLanguage> {
    public ProgrammingLanguageRepository() {
        super(ProgrammingLanguage.class);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}


