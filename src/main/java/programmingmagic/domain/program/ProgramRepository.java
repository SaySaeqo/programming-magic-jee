package programmingmagic.domain.program;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import programmingmagic.base.BaseRepository;

@Dependent
public class ProgramRepository extends BaseRepository<Program> {
    public ProgramRepository() {
        super(Program.class);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}


