package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programminglanguage

import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseService

@RequestScoped
class ProgrammingLanguageService extends BaseService<ProgrammingLanguageRepository, ProgrammingLanguage> {

    ProgrammingLanguageService(){super()}
    @Inject ProgrammingLanguageService(ProgrammingLanguageRepository repository){super(repository)}
}
