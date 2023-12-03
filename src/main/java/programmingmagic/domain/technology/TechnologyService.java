package programmingmagic.domain.technology;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import programmingmagic.base.BaseService;

@LocalBean
@Stateless
public class TechnologyService extends BaseService<TechnologyRepository, Technology> {
    public TechnologyService() {
        super();
    }

    @Inject
    public TechnologyService(TechnologyRepository repository) {
        super(repository);
    }
}


