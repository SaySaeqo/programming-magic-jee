package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.technology

import jakarta.ejb.LocalBean
import jakarta.ejb.Stateless
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseService

@LocalBean
@Stateless
class TechnologyService extends BaseService<TechnologyRepository, Technology>{

    TechnologyService(){super()}

    @Inject
    TechnologyService(TechnologyRepository repository){ super(repository) }
}
