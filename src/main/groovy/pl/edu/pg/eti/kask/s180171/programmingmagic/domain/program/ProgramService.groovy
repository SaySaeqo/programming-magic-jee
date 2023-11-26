package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program

import jakarta.ejb.LocalBean
import jakarta.ejb.Stateless
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseService

@LocalBean
@Stateless
class ProgramService extends BaseService<ProgramRepository, Program> {

    ProgramService(){super()}
    @Inject ProgramService(ProgramRepository repository){super(repository)}
}
