package programmingmagic.security;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import programmingmagic.base.BaseEntityConverter;

//@FacesConverter(forClass = User.class, managed = true)
public class UserConverter  implements Converter<User> {

    private final BaseEntityConverter<UserService, User> converter;


    @Inject
    public UserConverter(UserService userService) {
        this.converter = new BaseEntityConverter<>(userService);
    }


    @Override
    public User getAsObject(FacesContext context, UIComponent component, String value) {
        return converter.getAsObject(context, component, value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, User value) {
        return converter.getAsString(context, component, value);
    }
}
