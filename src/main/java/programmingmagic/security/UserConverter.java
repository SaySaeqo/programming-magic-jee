package programmingmagic.security;

import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@FacesConverter(forClass = User.class, managed = true)
@NoArgsConstructor
public class UserConverter  implements Converter<User> {

    private UserService userService;


    @EJB
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public User getAsObject(FacesContext context, UIComponent component, String value) {
        return value != null ? userService.get(UUID.fromString(value)) : null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, User value) {
        return value != null ? value.getUuid().toString() : "";
    }
}
