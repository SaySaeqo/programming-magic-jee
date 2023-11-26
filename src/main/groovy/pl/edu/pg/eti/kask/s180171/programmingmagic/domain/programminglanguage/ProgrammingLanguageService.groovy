package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programminglanguage

import jakarta.ejb.LocalBean
import jakarta.ejb.Stateless
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseService

@LocalBean
@Stateless
class ProgrammingLanguageService extends BaseService<ProgrammingLanguageRepository, ProgrammingLanguage> {

    ProgrammingLanguageService(){super()}
    @Inject ProgrammingLanguageService(ProgrammingLanguageRepository repository){super(repository)}
}
