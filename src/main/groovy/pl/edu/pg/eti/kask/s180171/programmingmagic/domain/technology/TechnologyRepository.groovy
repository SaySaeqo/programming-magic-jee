package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.technology

import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import pl.edu.pg.eti.kask.s180171.programmingmagic.DataStore
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseRepository

@RequestScoped
class TechnologyRepository extends BaseRepository<Technology>{

    TechnologyRepository(){super()}
    @Inject TechnologyRepository(DataStore dataStore) { super(dataStore, Technology.class) }
}
