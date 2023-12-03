package programmingmagic.security;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

@Named
@RequestScoped
public class UserViewController {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final HttpServletRequest request;
    private final SecurityContext securityContext;
    private final FacesContext facesContext;
    private UserService userService;
    @Setter
    @Getter
    private String login;
    @Setter
    @Getter
    private String password;

    @Inject
    public UserViewController(HttpServletRequest request, SecurityContext securityContext, FacesContext facesContext) {
        this.request = request;
        this.securityContext = securityContext;
        this.facesContext = facesContext;
    }

    @EJB
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String logoutAction() throws ServletException {
        request.logout();//Session invalidate can possibly not work with JASPIC.
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }

    public Collection<User> getUsers() {
        return userService.getAll();
    }

    public void delete(User user) {
        userService.delete(user);
    }

    public void signIn() {
        userService.getAll().forEach(user -> log.info("User: {}", user));
        log.info("Sign in attempt for user: {}", login);
        log.info("Password: {}", password);
        AuthenticationStatus status = securityContext.authenticate(request, extractResponseFromFacesContext(),
                withParams().credential(new UsernamePasswordCredential(login, new Password(password))));
        log.info("Status: {}", status);
        facesContext.responseComplete();
    }

    private HttpServletResponse extractResponseFromFacesContext() {
        return (HttpServletResponse) facesContext.getExternalContext().getResponse();
    }
}
