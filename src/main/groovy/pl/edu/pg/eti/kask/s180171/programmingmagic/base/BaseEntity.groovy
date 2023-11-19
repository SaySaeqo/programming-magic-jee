package pl.edu.pg.eti.kask.s180171.programmingmagic.base

import groovy.transform.AutoClone
import groovy.transform.AutoCloneStyle
import groovy.transform.EqualsAndHashCode
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType

@AutoClone(style= AutoCloneStyle.SERIALIZATION)
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class BaseEntity implements Serializable{
    @Id
    UUID uuid
}
