package pl.edu.pg.eti.kask.s180171.programmingmagic.base


import jakarta.validation.constraints.NotNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pl.edu.pg.eti.kask.s180171.programmingmagic.DataStore

import java.lang.invoke.WrongMethodTypeException
import java.lang.reflect.ParameterizedType

abstract class BaseRepository<T extends BaseEntity> {

    Logger log = LoggerFactory.getLogger(this.class.simpleName)
    T meGeneric
    DataStore dataStore

    BaseRepository(DataStore dataStore){
        this.dataStore = dataStore

        // initialize findBy methods
        meGeneric = (
                        (Class)(
                            (ParameterizedType)this.class.genericSuperclass
                        ).actualTypeArguments[0]
                    ).declaredConstructors[0].newInstance() as T
        def entity = new BaseEntity(){}
        meGeneric.properties.each {key, value->
            if (entity.properties.containsKey(key)) return
            def methodName = "findBy${key.toString().capitalize()}"
            log.info methodName
            Closure<List<T>> method = { criteria ->
                if (criteria.class != value.class) throw new WrongMethodTypeException(
                        "Method $methodName from ${this.class.name} expect argument type of $value.class" +
                                " instead of $criteria.class.simpleName")
                table.findAll {it."$key" == criteria}.asList()
            }

            this.metaClass."$methodName" = method
        }
    }

    HashSet<T> getTable(){ dataStore.getTable(meGeneric.class) }

    List<T> findAll(){ table.toList() }

    T findByUUID(UUID uuid){ table.find {uuid == it.uuid} }

    T save(@NotNull T entity){
        dataStore.saveEntity(entity)
    }

}
