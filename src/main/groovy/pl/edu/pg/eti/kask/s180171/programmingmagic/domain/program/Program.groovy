package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program

import groovy.transform.ToString
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntity
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.technology.Technology

import java.time.LocalDate

@ToString(includePackage = false)
class Program extends BaseEntity{

    String name
    String description
    String code
    LocalDate dateOfCreation

    Technology usedTechnology
    Programmer author

}
