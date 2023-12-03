package programmingmagic.domain.program;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import programmingmagic.base.BaseService;

@LocalBean
@Stateless
public class ProgramService extends BaseService<ProgramRepository, Program> {

    public ProgramService(){super();}
    @Inject public ProgramService(ProgramRepository repository){super(repository);}
}
