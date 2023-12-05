package programmingmagic.security;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import programmingmagic.base.BaseRepository;
import programmingmagic.security.User;

import java.util.Optional;

@Dependent
public class UserRepository extends BaseRepository<User> {

    public UserRepository() {
        super(User.class);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User findByLogin(String login) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> q = cb.createQuery(User.class);
            Root<User> user = q.from(User.class);

            ParameterExpression<String> loginParameter = cb.parameter(String.class);
            q.select(user)
                    .where(cb.equal(user.get("login"), loginParameter));

            return entityManager.createQuery(q).setParameter(loginParameter,login).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
