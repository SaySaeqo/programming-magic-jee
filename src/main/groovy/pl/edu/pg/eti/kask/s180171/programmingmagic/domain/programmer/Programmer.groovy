package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import groovy.transform.CompileStatic
import groovy.transform.ToString
import groovy.transform.TupleConstructor
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntity
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.model.ProgrammerLevel

import java.time.LocalDate

@ToString
@Entity
@CompileStatic
class Programmer extends BaseEntity{

    @Id
    UUID uuid = UUID.randomUUID()

    String name
    String title
    LocalDate birthday
    ProgrammerLevel level

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    List<Program> applications

    Programmer(){}

    Programmer(String name, LocalDate birthday, ProgrammerLevel level){
        this.name = name
        this.birthday = birthday
        this.level = level
    }
}
