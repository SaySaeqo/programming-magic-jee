package pl.edu.pg.eti.kask.s180171.programmingmagic.base

import jakarta.persistence.EntityManager
import jakarta.validation.constraints.NotNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BaseRepository<T extends BaseEntity>{

    Logger log = LoggerFactory.getLogger(this.class.simpleName)
    Class<T> clazz

    EntityManager _entityManager

    BaseRepository(){}

    BaseRepository(Class<T> clazz){
        this.clazz = clazz
    }

    List<T> findAll(){
        def sql = "select x from $clazz.simpleName x"
        _entityManager.createQuery(sql).resultList
    }

    T findById(UUID uuid){ _entityManager.find(clazz, uuid) }

    T save(@NotNull T entity){
        if (findById(entity.uuid)) entity = _entityManager.merge entity
        else _entityManager.persist entity
        entity
    }

    void delete(@NotNull T entity){ _entityManager.remove findById(entity.uuid) }

}
