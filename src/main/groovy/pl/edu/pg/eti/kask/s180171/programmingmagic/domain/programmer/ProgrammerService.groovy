package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import jakarta.enterprise.context.ConversationScoped
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseService
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program
import pl.edu.pg.eti.kask.s180171.programmingmagic.exception.EntityNotFoundException

@RequestScoped
class ProgrammerService extends BaseService<ProgrammerRepository, Programmer>{

    ProgrammerService(){super()}
    @Inject ProgrammerService(ProgrammerRepository repository){ super(repository) }

    def getPortrait(Programmer forProgrammer) { repository.getPortrait(forProgrammer) }
    void deletePortrait(Programmer forProgrammer){ repository.deletePortrait(forProgrammer)}
    void savePortrait(InputStream inputStream, Programmer forProgrammer){ repository.savePortrait(inputStream,forProgrammer)}

    Programmer getByName(String name){
        List<Programmer> result = repository.findByName(name)
        if (result.empty) throw new EntityNotFoundException(repository.entity.class, "name", name)
        result.first()
    }

    List<Program> getApplications(Programmer programmer){
        repository.linkToList(Program, "applications", programmer)
        programmer.applications
    }
}
