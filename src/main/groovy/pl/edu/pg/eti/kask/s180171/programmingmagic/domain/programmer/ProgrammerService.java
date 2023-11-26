package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseService;
import pl.edu.pg.eti.kask.s180171.programmingmagic.exception.EntityNotFoundException;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;


@LocalBean
@Stateless
@Default
public class ProgrammerService{

    private Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
    private BaseService<ProgrammerRepository, Programmer> baseService;

    public BaseService<ProgrammerRepository, Programmer> getBaseService(){ return baseService; }

    public ProgrammerService(){}
    @Inject public ProgrammerService(ProgrammerRepository repository){
        baseService = new BaseService<ProgrammerRepository, Programmer>(repository);
    }

    public byte[] getPortrait(Programmer forProgrammer) { return baseService.getRepository().getPortrait(forProgrammer); }
    public void deletePortrait(Programmer forProgrammer){ baseService.getRepository().deletePortrait(forProgrammer);}
    public void savePortrait(InputStream inputStream, Programmer forProgrammer){ baseService.getRepository().savePortrait(inputStream,forProgrammer);}

    public Programmer getByName(String name) throws EntityNotFoundException{
        List<Programmer> result = baseService.getRepository().findByName(name);
        if (result.isEmpty()) throw new EntityNotFoundException(Programmer.class, "name", name);
        return result.get(0);
    }
    public List<Programmer> getAll(){
        return baseService.getAll();
    }
    public Programmer get(UUID uuid){
        return baseService.get(uuid);
    }

    public Programmer save(@NotNull Programmer entity){
        return baseService.save(entity);
    }

    public void delete(@NotNull Programmer entity){
        baseService.delete(entity);
    }
}
