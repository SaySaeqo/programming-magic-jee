package programmingmagic.domain.programminglanguage;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import programmingmagic.base.BaseService;
import programmingmagic.base.Service;

@LocalBean
@Stateless
@NoArgsConstructor
public class ProgrammingLanguageService extends BaseService<ProgrammingLanguageRepository, ProgrammingLanguage> {


    @Inject
    public ProgrammingLanguageService(ProgrammingLanguageRepository repository) {
        super(repository);
    }
}


