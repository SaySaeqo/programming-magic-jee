package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import groovy.transform.ToString
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntity
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.model.ProgrammerLevel

@ToString
class Programmer extends BaseEntity{

    String name
    String title
    Date birthday
    ProgrammerLevel level

    List<Program> applications

}
