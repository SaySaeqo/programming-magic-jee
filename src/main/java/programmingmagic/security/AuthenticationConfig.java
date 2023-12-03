package programmingmagic.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 * Configuration class for security context. There are three authentication mechanism and only one can be used at a
 * time:
 * <ul>
 *     <li>{@link BasicAuthenticationMechanismDefinition} every secured resource is secured with basic auth mechanism,
 *     ideal for REST services (JAX-RS and Servlet). No login forms work but rest services can be used from clients.</li>
 *     <li>{@link FormAuthenticationMechanismDefinition} every secured resource is secured with form auth mechanism,
 *     ideal for HTML web pages (JSF). REST services can not be called from clients as form auth is required.</li>
 *     <li>{@link CustomFormAuthenticationMechanismDefinition} every secured resource is secured with form auth mechanism,
 *     auth form can used custom backend bean method.</li>
 * </ul>
 * Both form based methods required {@link LoginToContinue} configuration.
 */
@ApplicationScoped
@BasicAuthenticationMechanismDefinition(realmName = "Programming Magic")
//@FormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                loginPage = "/login.xhtml",
//                errorPage = "/login_error.xhtml"
//        )
//)
//@CustomFormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                loginPage = "/login.xhtml",
//                errorPage = "/login_error.xhtml"
//        )
//)
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/ProgrammingMagic",
        callerQuery = "select a.password from app_user a where a.login = ?",
        groupsQuery = "select r.roles from user_role r where r.uuid = (select a.uuid from app_user a where a.login = ?)",
        hashAlgorithm = Pbkdf2PasswordHash.class
)
public class AuthenticationConfig {
}
