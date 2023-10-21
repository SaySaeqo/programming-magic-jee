package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program

import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntity
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.technology.Technology

class Program extends BaseEntity{

    String name
    String code
    Date dateOfCreation

    Technology usedTechnology
    Programmer author

}
