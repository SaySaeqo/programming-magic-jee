package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseService

@RequestScoped
class ProgrammerService extends BaseService<ProgrammerRepository, Programmer> {

    ProgrammerService(){super()}
    @Inject ProgrammerService(ProgrammerRepository repository){ super(repository) }

    def getPortrait(Programmer forProgrammer) { repository.getPortrait(forProgrammer) }
    void deletePortrait(Programmer forProgrammer){ repository.deletePortrait(forProgrammer)}
    void savePortrait(InputStream inputStream, Programmer forProgrammer){ repository.savePortrait(inputStream,forProgrammer)}
}
