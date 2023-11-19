package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer


import jakarta.enterprise.context.RequestScoped
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.validation.constraints.NotNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseService
import pl.edu.pg.eti.kask.s180171.programmingmagic.exception.EntityNotFoundException

import java.time.LocalDate

@RequestScoped
@Default
class ProgrammerService{

    Logger log = LoggerFactory.getLogger(ProgrammerService.class.simpleName)
    BaseService<ProgrammerRepository, Programmer> baseService

    ProgrammerService(){}
    @Inject ProgrammerService(ProgrammerRepository repository){
        baseService = new BaseService(repository)
    }

    byte[] getPortrait(Programmer forProgrammer) { baseService.repository.getPortrait(forProgrammer) }
    void deletePortrait(Programmer forProgrammer){ baseService.repository.deletePortrait(forProgrammer)}
    void savePortrait(InputStream inputStream, Programmer forProgrammer){ baseService.repository.savePortrait(inputStream,forProgrammer)}

    @Transactional
    Programmer getByName(String name) throws EntityNotFoundException{
        List<Programmer> result = baseService.repository.findByName(name)
        if (result.empty) throw new EntityNotFoundException(Programmer.class, "name", name)
        result.first()
    }
    @Transactional
    List<Programmer> getAll(){
        baseService.all
    }
    @Transactional
    Programmer get(UUID uuid){
        baseService.get(uuid)
    }

    @Transactional
    Programmer save(@NotNull Programmer entity){
        baseService.save(entity)
    }

    @Transactional
    void delete(@NotNull Programmer entity){
        baseService.delete(entity)
    }
}
