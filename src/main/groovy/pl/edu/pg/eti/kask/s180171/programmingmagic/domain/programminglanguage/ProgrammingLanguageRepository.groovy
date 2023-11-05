package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programminglanguage

import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import pl.edu.pg.eti.kask.s180171.programmingmagic.DataStore
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseRepository

@RequestScoped
class ProgrammingLanguageRepository extends BaseRepository<ProgrammingLanguage>{

    ProgrammingLanguageRepository(){super()}
    @Inject ProgrammingLanguageRepository(DataStore dataStore){super(dataStore, ProgrammingLanguage.class)}
}
