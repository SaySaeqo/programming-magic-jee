package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program

import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import pl.edu.pg.eti.kask.s180171.programmingmagic.DataStore
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseRepository
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerRepository

@RequestScoped
class ProgramRepository extends BaseRepository<Program>{

    ProgramRepository(){super()}
    @Inject ProgramRepository(DataStore dataStore){super(dataStore,Program.class)}
}
