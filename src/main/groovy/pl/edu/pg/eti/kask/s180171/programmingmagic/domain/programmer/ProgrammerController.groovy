package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.constraints.NotNull
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.HttpRequestException
import pl.edu.pg.eti.kask.s180171.programmingmagic.exception.EntityNotFoundException

@RequestScoped
class ProgrammerController {
    ProgrammerRepository repository;

    ProgrammerController(){

    }

    @Inject
    ProgrammerController(ProgrammerRepository repository){
        this.repository = repository;
    }

    Programmer getProgrammer(UUID uuid){
        def result = repository.findByUUID(uuid)
        if (result) return result
        else throw new EntityNotFoundException(Programmer, uuid)
    }

    List<Programmer> getProgrammers(){ repository.findAll() }

    Programmer saveProgrammer(@NotNull Programmer programmer){ repository.save(programmer)}

    def getPortrait(Programmer forProgrammer) { repository.getPortrait(forProgrammer) }
    void deletePortrait(Programmer forProgrammer){ repository.deletePortrait(forProgrammer)}
    void savePortrait(InputStream inputStream, Programmer forProgrammer){ repository.savePortrait(inputStream,forProgrammer)}
}
