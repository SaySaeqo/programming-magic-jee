package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.technology

import groovy.transform.TupleConstructor
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntity
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programminglanguage.ProgrammingLanguage

import java.time.LocalDate

@Entity
class Technology extends BaseEntity{

    @Id
    UUID uuid = UUID.randomUUID()

    String name
    Boolean isFramework
    LocalDate dateOfCreation

    @ManyToOne
    @JoinColumn(name = "programming_language_id")
    ProgrammingLanguage language
    @OneToMany(mappedBy = "usedTechnology")
    List<Program> applications

    Technology(){}
    Technology(String name, Boolean isFramework, LocalDate dateOfCreation){
        this.name = name
        this.isFramework = isFramework
        this.dateOfCreation = dateOfCreation
    }
}
