package programmingmagic.security;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
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
            return entityManager.createQuery("select u from User u where u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
