package programmingmagic.base;

import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class BaseRepository<T extends BaseEntity> {

    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public Class<T> getClazz() {
        return clazz;
    }

    Class<T> clazz;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    protected EntityManager entityManager;

    public BaseRepository(){}

    public BaseRepository(Class<T> clazz){
        this.clazz = clazz;
    }

    public List<T> findAll(){
        String sql = "select x from " + clazz.getSimpleName() + " x";
        return entityManager.createQuery(sql, clazz).getResultList();
    }

    public T findById(UUID uuid){ return entityManager.find(clazz, uuid); }

    public T save(@NotNull T entity){
        if (findById(entity.getUuid()) != null) entity = entityManager.merge(entity);
        else entityManager.persist(entity);
        return entity;
    }

    public void delete(@NotNull T entity){ entityManager.remove(findById(entity.getUuid())); }
}