package programmingmagic.domain.programmer;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import programmingmagic.base.BaseService;
import programmingmagic.base.Service;
import programmingmagic.exception.EntityNotFoundException;
import programmingmagic.security.UserRoles;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;


@LocalBean
@Stateless
@NoArgsConstructor
@DeclareRoles({UserRoles.ADMIN, UserRoles.USER})
public class ProgrammerService implements Service<Programmer> {

    private Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
    private BaseService<ProgrammerRepository, Programmer> baseService;

    @Inject public ProgrammerService(ProgrammerRepository repository){
        baseService = new BaseService<ProgrammerRepository, Programmer>(repository);
    }

    @PermitAll
    public byte[] getPortrait(Programmer forProgrammer) { return baseService.getRepository().getPortrait(forProgrammer); }
    @RolesAllowed(UserRoles.ADMIN)
    public void deletePortrait(Programmer forProgrammer){ baseService.getRepository().deletePortrait(forProgrammer);}
    @RolesAllowed(UserRoles.ADMIN)
    public void savePortrait(InputStream inputStream, Programmer forProgrammer){ baseService.getRepository().savePortrait(inputStream,forProgrammer);}

    @PermitAll
    public Programmer getByName(String name) throws EntityNotFoundException {
        List<Programmer> result = baseService.getRepository().findByName(name);
        if (result.isEmpty()) return null;
        return result.get(0);
    }
    @PermitAll
    public List<Programmer> getAll(){
        return baseService.getAll();
    }
    @PermitAll
    public Programmer get(UUID uuid){
        return baseService.get(uuid);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public Programmer save(@Valid Programmer entity){
        return baseService.save(entity);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void delete(@NotNull Programmer entity){
        baseService.delete(entity);
    }

    public void clearCache(){
        baseService.getRepository().getEntityManager().getEntityManagerFactory().getCache().evictAll();
        baseService.getRepository().getEntityManager().clear();
    }
}
