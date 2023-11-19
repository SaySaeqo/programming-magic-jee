package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program

import groovy.transform.ToString
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntity
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.technology.Technology

import java.time.LocalDate

@ToString(includePackage = false)
@Entity
class Program extends BaseEntity{

    @Id
    UUID uuid = UUID.randomUUID()

    String name
    String description
    String code
    LocalDate dateOfCreation

    @ManyToOne
    @JoinColumn(name = "technology_id")
    Technology usedTechnology

    @ManyToOne
    @JoinColumn(name = "author_id")
    Programmer author

}
