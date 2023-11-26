package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.technology

import jakarta.enterprise.context.Dependent
import jakarta.enterprise.context.RequestScoped
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseRepository

@Dependent
class TechnologyRepository extends BaseRepository<Technology>{

    TechnologyRepository() { super(Technology.class) }

    @PersistenceContext
    void setEntityManager(EntityManager entityManager){
        this._entityManager = entityManager
    }
}
