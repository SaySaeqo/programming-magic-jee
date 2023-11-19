package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseService

@RequestScoped
class ProgramService extends BaseService<ProgramRepository, Program> {

    ProgramService(){super()}
    @Inject ProgramService(ProgramRepository repository){super(repository)}
}
