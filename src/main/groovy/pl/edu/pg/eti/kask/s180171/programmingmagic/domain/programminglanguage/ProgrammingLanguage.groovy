package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programminglanguage

import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntity
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programminglanguage.model.ProgrammingLanguageType
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.technology.Technology

class ProgrammingLanguage extends BaseEntity{

    String name
    ProgrammingLanguageType type
    Boolean isCompiled
    Boolean isStrongTyped

    List<Technology> technologies

}