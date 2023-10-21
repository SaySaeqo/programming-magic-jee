package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import pl.edu.pg.eti.kask.s180171.programmingmagic.DataStore
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseRepository

class ProgrammerRepository extends BaseRepository<Programmer> {

    ProgrammerRepository(DataStore dataStore) {
        super(dataStore)
    }

}
