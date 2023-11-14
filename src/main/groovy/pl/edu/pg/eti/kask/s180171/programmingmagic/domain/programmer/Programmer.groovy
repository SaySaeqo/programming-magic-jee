package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import groovy.transform.ToString
import jakarta.inject.Inject
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntity
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.ProgramRepository
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.model.ProgrammerLevel

import java.time.LocalDate

@ToString
class Programmer extends BaseEntity{

    String name
    String title
    LocalDate birthday
    ProgrammerLevel level

    List<Program> applications

//    int getAge(){
//        if (birthday == null) return null
//        LocalDate.now().year - birthday.year
//    }

}
