package pl.edu.pg.eti.kask.s180171.programmingmagic.base


import jakarta.validation.constraints.NotNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pl.edu.pg.eti.kask.s180171.programmingmagic.DataStore

import java.lang.invoke.WrongMethodTypeException


class BaseRepository<T extends BaseEntity>{

    Logger log = LoggerFactory.getLogger(this.class.simpleName)
    T entity
    DataStore dataStore

    BaseRepository(){}

    BaseRepository(DataStore dataStore, Class<T> tClass){
        this.dataStore = dataStore

        this.entity = tClass.declaredConstructors[0].newInstance() as T
        def baseEntity = new BaseEntity(){}

        // initialize findBy methods
        this.entity.properties.each { key, value->
            if (baseEntity.properties.containsKey(key)) return
            def methodName = "findBy${key.toString().capitalize()}"
            Closure<List<T>> method = { criteria ->
                if (criteria.class != value.class) throw new WrongMethodTypeException(
                        "Method $methodName from ${this.class.name} expect argument type of $value.class" +
                                " instead of $criteria.class.simpleName")
                table.findAll {it."$key" == criteria}.asList()
            }

            this.metaClass."$methodName" = method
        }
    }

    HashSet<T> getTable(){ dataStore.getCopyOfTable(entity.class) }

    List<T> findAll(){ table.toList() }

    T findByUUID(UUID uuid){ table.find {uuid == it.uuid} }

    T save(@NotNull T entity){
        dataStore.save(entity)
    }

}
