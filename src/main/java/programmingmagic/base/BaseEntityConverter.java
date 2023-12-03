package programmingmagic.base;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import java.util.UUID;

public class BaseEntityConverter<S extends Service<T>,T extends BaseEntity> implements Converter<T> {
    private S service;

    public BaseEntityConverter(S service){
        this.service = service;
    }

    @Override
    public T getAsObject(FacesContext context, UIComponent component, String value) {
        return value != null ? service.get(UUID.fromString(value)) : null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, T value) {
        return value != null ? value.getUuid().toString() : "";
    }
}


