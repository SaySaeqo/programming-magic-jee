package pl.edu.pg.eti.kask.s180171.programmingmagic.base

import jakarta.faces.component.UIComponent
import jakarta.faces.context.FacesContext
import jakarta.faces.convert.Converter
import jakarta.faces.convert.FacesConverter

@FacesConverter(forClass = UUID.class)
class UuidConverter implements Converter<UUID> {

    @Override
    UUID getAsObject(FacesContext context, UIComponent component, String value) {
        return UUID.fromString(value);
    }

    @Override
    String getAsString(FacesContext context, UIComponent component, UUID value) {
        return value.toString();
    }

}