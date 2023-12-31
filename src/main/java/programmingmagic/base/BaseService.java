package programmingmagic.base;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import programmingmagic.exception.EntityNotFoundException;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class BaseService<R extends BaseRepository<T>, T extends BaseEntity> {
    private Logger log = LoggerFactory.getLogger(BaseService.class.getSimpleName());

    @Getter
    private R repository;

    public BaseService() {}

    public BaseService(R repository) {
        this.repository = repository;
    }

    public T get(UUID uuid) {
        repository.getEntityManager().getEntityManagerFactory().getCache().evictAll();
        repository.getEntityManager().clear();
        T result = repository.findById(uuid);
        if (result == null) {
            throw new EntityNotFoundException(repository.getClazz(), uuid);
        }
        return result;
    }

    public List<T> getAll() {
        repository.getEntityManager().getEntityManagerFactory().getCache().evictAll();
        repository.getEntityManager().clear();
        return repository.findAll();
    }

    public T save(@NotNull T entity) {
        return repository.save(entity);
    }

    public void delete(@NotNull T entity) {
        repository.delete(entity);
    }
}


