package programmingmagic.security;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.NoArgsConstructor;
import programmingmagic.base.BaseService;
import programmingmagic.base.Service;

import java.util.List;
import java.util.UUID;

@LocalBean
@Stateless
@Default
@NoArgsConstructor
public class UserService implements Service<User> {

    BaseService<UserRepository, User> baseService;
    Pbkdf2PasswordHash pbkdf2PasswordHash;

    @Inject
    public UserService(UserRepository userRepository, Pbkdf2PasswordHash pbkdf2PasswordHash) {
        this.baseService = new BaseService<>(userRepository);
        this.pbkdf2PasswordHash = pbkdf2PasswordHash;
    }

    @RolesAllowed(UserRoles.ADMIN)
    public User getByLogin(String login) {
        return baseService.getRepository().findByLogin(login);
    }

    @RolesAllowed(UserRoles.ADMIN)
    @Override
    public User save(User user) {
        return baseService.save(user);
    }

    @RolesAllowed(UserRoles.ADMIN)
    @Override
    public void delete(User user) {
        baseService.delete(user);
    }

    @RolesAllowed(UserRoles.ADMIN)
    @Override
    public User get(UUID uuid) {
        return baseService.getRepository().findById(uuid);
    }

//    @RolesAllowed(UserRoles.ADMIN)
    @RolesAllowed(UserRoles.ADMIN)
    @Override
    public List<User> getAll() {
        return baseService.getRepository().findAll();
    }

    @PermitAll
    public boolean login(String login, String password) {
        User user = getByLogin(login);
        return pbkdf2PasswordHash.verify(password.toCharArray(), user.getPassword());
    }
}
