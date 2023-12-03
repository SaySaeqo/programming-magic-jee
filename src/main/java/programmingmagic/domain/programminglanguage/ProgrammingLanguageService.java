package programmingmagic.domain.programminglanguage;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import programmingmagic.base.BaseService;

@LocalBean
@Stateless
public class ProgrammingLanguageService extends BaseService<ProgrammingLanguageRepository, ProgrammingLanguage> {
    public ProgrammingLanguageService() {
        super();
    }

    @Inject
    public ProgrammingLanguageService(ProgrammingLanguageRepository repository) {
        super(repository);
    }
}


