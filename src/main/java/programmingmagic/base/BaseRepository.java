package programmingmagic.base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(clazz);
        Root<T> root = q.from(clazz);

        q.select(root);

        TypedQuery<T> query = entityManager.createQuery(q);
        return query.getResultList();
    }

    public T findById(UUID uuid){ return entityManager.find(clazz, uuid); }

    public T save(@NotNull T entity){
        entity.setEntityModificationDate(LocalDateTime.now());
        if (findById(entity.getUuid()) != null) entity = entityManager.merge(entity);
        else entityManager.persist(entity);
        return entity;
    }

    public void delete(@NotNull T entity){ entityManager.remove(findById(entity.getUuid())); }
}