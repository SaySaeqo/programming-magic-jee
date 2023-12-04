package programmingmagic.init;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import programmingmagic.security.User;
import programmingmagic.security.UserRepository;
import programmingmagic.security.UserRoles;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * EJB singleton can be forced to start automatically when application starts. Injects proxy to the services and fills
 * database with default content. When using persistence storage application instance should be initialized only during
 * first run in order to init database with starting data. Good place to create first default admin user.
 */
@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@NoArgsConstructor(force = true)
public class InitializeAdminService {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final UserRepository userRepository;
    private final Pbkdf2PasswordHash passwordHash;
    @Inject
    public InitializeAdminService(UserRepository userRepository, Pbkdf2PasswordHash passwordHash) {
        this.userRepository = userRepository;
        this.passwordHash = passwordHash;
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should be
     * created only once.
     */
    @PostConstruct
    private void init() {
        if (userRepository == null || passwordHash == null) {
            throw new IllegalStateException("Not injected correctly");
        }
        if (userRepository.findByLogin("admin-service") == null) {

            User admin = User.builder()
                    .login("admin-service")
                    .password(passwordHash.generate("adminadmin".toCharArray()))
                    .roles(List.of(UserRoles.ADMIN, UserRoles.USER))
                    .build();

            log.info("Created admin user: {}", admin);

            userRepository.save(admin);
        }
    }

}
