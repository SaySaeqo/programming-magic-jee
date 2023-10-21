package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.constraints.NotNull
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.HttpRequestException
import pl.edu.pg.eti.kask.s180171.programmingmagic.exception.EntityNotFoundException

class ProgrammerController {
    ProgrammerRepository repository;

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

    byte[] getProgrammerPortrait(UUID uuid){
        def result = getProgrammer(uuid).portrait
        if (result) return result
        else throw new HttpRequestException(
                HttpServletResponse.SC_NOT_FOUND,
                "There is no portrait for programmer with uuid $uuid")
    }
}
