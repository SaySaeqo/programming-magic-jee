package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programminglanguage

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntity
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programminglanguage.model.ProgrammingLanguageType
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.technology.Technology

@Entity
class ProgrammingLanguage extends BaseEntity{

    @Id
    UUID uuid = UUID.randomUUID()

    String name
    ProgrammingLanguageType type
    Boolean isCompiled
    Boolean isStrongTyped

    @OneToMany(mappedBy = "language")
    List<Technology> technologies

}