package pl.edu.pg.eti.kask.s180171.programmingmagic

import jakarta.validation.constraints.NotNull
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntity


class DataStore {

    private Map<Class, HashSet<? extends BaseEntity>> tables = [:]

    DataStore(){}

    <T extends BaseEntity> void addTable(Class<T> clazz){
        tables.put clazz, new HashSet<T>()
    }

    <T extends BaseEntity> HashSet<T> getTable(Class<T> clazz){
        tables.get(clazz).collect {it.clone()}
    }

    <T extends BaseEntity> void removeEntity(@NotNull T entity){
        tables.get(entity.class).removeIf {it.uuid == entity.uuid}
    }

    <T extends BaseEntity> void saveEntity(@NotNull T entity){
        removeEntity(entity)
        tables.get(entity.class).add(entity)
    }


}
