package pl.edu.pg.eti.kask.s180171.programmingmagic.base

import jakarta.validation.constraints.NotNull
import pl.edu.pg.eti.kask.s180171.programmingmagic.exception.EntityNotFoundException

class BaseService<R extends BaseRepository<T>, T extends BaseEntity>{

    R repository

    BaseService(){}

    BaseService(R repository){
        this.repository = repository
    }

    T get(UUID uuid){
        def result = repository.findByUUID(uuid)
        if (result == null) throw new EntityNotFoundException(repository.entity.class, uuid)
        result
    }

    List<T> getAll(){
        repository.findAll()
    }

    T save(@NotNull T entity){
        repository.save(entity)
    }


}
