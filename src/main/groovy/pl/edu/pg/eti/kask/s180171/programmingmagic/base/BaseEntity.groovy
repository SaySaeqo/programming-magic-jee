package pl.edu.pg.eti.kask.s180171.programmingmagic.base

import groovy.transform.AutoClone
import groovy.transform.AutoCloneStyle

@AutoClone(style= AutoCloneStyle.SERIALIZATION)
abstract class BaseEntity implements Serializable{
    UUID uuid = UUID.randomUUID()
}
