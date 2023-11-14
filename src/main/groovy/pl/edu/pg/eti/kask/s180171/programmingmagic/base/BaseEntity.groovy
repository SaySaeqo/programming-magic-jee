package pl.edu.pg.eti.kask.s180171.programmingmagic.base

import groovy.transform.AutoClone
import groovy.transform.AutoCloneStyle
import groovy.transform.EqualsAndHashCode

@AutoClone(style= AutoCloneStyle.SERIALIZATION)
@EqualsAndHashCode
abstract class BaseEntity implements Serializable{
    UUID uuid = UUID.randomUUID()
}
