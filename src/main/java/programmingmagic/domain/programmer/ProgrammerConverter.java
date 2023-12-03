package programmingmagic.domain.programmer;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.util.UUID;

@FacesConverter(forClass = Programmer.class, managed = true)
public class ProgrammerConverter implements Converter<Programmer> {
    private ProgrammerService service;

    @Inject
    public ProgrammerConverter(ProgrammerService service){
        this.service = service;
    }

    @Override
    public Programmer getAsObject(FacesContext context, UIComponent component, String value) {
        return value != null ? service.get(UUID.fromString(value)) : null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Programmer value) {
        return value != null ? value.getUuid().toString() : "";
    }
}


