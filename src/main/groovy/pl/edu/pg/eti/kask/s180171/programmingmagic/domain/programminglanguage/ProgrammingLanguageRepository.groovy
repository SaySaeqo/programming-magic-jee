package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programminglanguage

import jakarta.enterprise.context.RequestScoped
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseRepository

@RequestScoped
class ProgrammingLanguageRepository extends BaseRepository<ProgrammingLanguage>{

    ProgrammingLanguageRepository(){super(ProgrammingLanguage.class)}

    @PersistenceContext
    void setEntityManager(EntityManager entityManager){
        this._entityManager = entityManager
    }
}
