package pl.edu.pg.eti.kask.s180171.programmingmagic

import jakarta.enterprise.context.ApplicationScoped
import jakarta.validation.constraints.NotNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntity

@ApplicationScoped
class DataStore {

    Logger log = LoggerFactory.getLogger(this.class.simpleName)

    private Map<Class, HashSet<? extends BaseEntity>> tables = [:]

    DataStore(){}

    private <T extends BaseEntity> HashSet<T> addTable(Class<T> clazz){
        def current = new HashSet<T>()
        tables.put clazz, current
        current
    }

    private <T extends BaseEntity> HashSet<T> getTable(Class<T> clazz){
        tables.get(clazz) ?: addTable(clazz)
    }

    synchronized <T extends BaseEntity> HashSet<T> getCopyOfTable(Class<T> clazz){
        getTable(clazz).collect {it.clone()}
    }

    synchronized <T extends BaseEntity> void removeByUuid(@NotNull T entity){
        tables.get(entity.class)?.removeIf {it.uuid == entity.uuid}
    }

    synchronized <T extends BaseEntity> T save(@NotNull T entity){
        removeByUuid(entity)
        getTable(entity.class as Class<T>).add(entity)
        entity
    }


}
