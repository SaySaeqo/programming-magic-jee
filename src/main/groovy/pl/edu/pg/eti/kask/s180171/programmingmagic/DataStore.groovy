package pl.edu.pg.eti.kask.s180171.programmingmagic

import jakarta.enterprise.context.ApplicationScoped
import jakarta.validation.constraints.NotNull
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntity

@ApplicationScoped
class DataStore {

    private Map<Class, HashSet<? extends BaseEntity>> tables = [:]

    DataStore(){}

    synchronized <T extends BaseEntity> void addTable(Class<T> clazz){
        tables.put clazz, new HashSet<T>()
    }

    synchronized <T extends BaseEntity> HashSet<T> getTable(Class<T> clazz){
        tables.get(clazz).collect {it.clone()}
    }

    synchronized <T extends BaseEntity> void removeEntity(@NotNull T entity){
        tables.get(entity.class).removeIf {it.uuid == entity.uuid}
    }

    synchronized <T extends BaseEntity> void saveEntity(@NotNull T entity){
        removeEntity(entity)
        tables.get(entity.class).add(entity)
    }


}
