package pl.edu.pg.eti.kask.s180171.programmingmagic.base


import jakarta.validation.constraints.NotNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pl.edu.pg.eti.kask.s180171.programmingmagic.exception.EntityNotFoundException

class BaseService<R extends BaseRepository<T>, T extends BaseEntity> implements Serializable{

    Logger log = LoggerFactory.getLogger(BaseService.class.simpleName)
    R repository

    BaseService(){}

    BaseService(R repository){
        this.repository = repository
    }

    T get(UUID uuid){
        repository._entityManager.entityManagerFactory.cache.evictAll()
        repository._entityManager.clear()
        def result = repository.findById(uuid)
        if (result == null) throw new EntityNotFoundException(repository.clazz, uuid)
        result
    }

    List<T> getAll(){
        repository._entityManager.entityManagerFactory.cache.evictAll()
        repository._entityManager.clear()
        repository.findAll()
    }
    T save(@NotNull T entity){ repository.save(entity) }
    void delete(@NotNull T entity){ repository.delete(entity) }


}
