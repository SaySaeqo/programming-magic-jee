package pl.edu.pg.eti.kask.s180171.programmingmagic.view

import jakarta.enterprise.context.ConversationScoped
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.ProgramService
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerService

@RequestScoped
@Named
class ProgramController implements Serializable{

    Logger log = LoggerFactory.getLogger(this.class.simpleName)

    ProgramService service
    ProgrammerService programmerService

    UUID programmerUuid
    UUID programUuid
    Program program = new Program() //?? czemu to jest potrzebne?
    Programmer programmer

    @Inject
    ProgramController(ProgramService service, ProgrammerService programmerService) {
        this.service = service
        this.programmerService = programmerService
    }

    List<Program> getPrograms() {
        programmerUuid? programmerService.getApplications(programmerService.get(programmerUuid)) : service.all
    }

    void initProgram(){
        program = programUuid ? service.get(programUuid) : new Program()
    }

    void initProgrammer(){
        if (programmerUuid == null) return
        programmer = programmerService.get(programmerUuid)
    }

    String delete(Program program) {
        service.delete(program)
        return "programs?faces-redirect=true"
    }

    String save() {
        program.author = programmerService.get(programmerUuid)
        service.save(program)
        return "programs?faces-redirect=true"
    }

    String cancel() {
        return "programs?faces-redirect=true"
    }
}
