package programmingmagic.domain.program;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import programmingmagic.base.BaseService;
import programmingmagic.base.Service;
import programmingmagic.security.UserRoles;

import java.util.List;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor
public class ProgramService implements Service<Program> {

    BaseService<ProgramRepository, Program> baseService;
    @Inject public ProgramService(ProgramRepository repository){
        baseService = new BaseService<>(repository);
    }

    @RolesAllowed(UserRoles.USER)
    @Override
    public Program get(UUID uuid){
        return baseService.get(uuid);
    }

    @RolesAllowed(UserRoles.USER)
    @Override
    public List<Program> getAll() {
        return baseService.getAll();
    }

    @RolesAllowed(UserRoles.USER)
    @Override
    public Program save(Program entity) {
        return baseService.save(entity);
    }

    @RolesAllowed(UserRoles.USER)
    @Override
    public void delete(Program entity) {
        baseService.delete(entity);
    }
}
