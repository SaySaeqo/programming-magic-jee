package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.technology

import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntity
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programminglanguage.ProgrammingLanguage

import java.time.LocalDate

class Technology extends BaseEntity{

    String name
    Boolean isFramework
    LocalDate dateOfCreation

    List<ProgrammingLanguage> languages
    List<Program> applications

}
