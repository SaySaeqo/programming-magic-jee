package programmingmagic.domain.programmer;

import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.UUID;

@FacesConverter(forClass = Programmer.class, managed = true)
@NoArgsConstructor
public class ProgrammerConverter implements Converter<Programmer> {
    private ProgrammerService service;

    @EJB
    public void setProgrammerService(ProgrammerService programmerService) {
        this.service = programmerService;
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


