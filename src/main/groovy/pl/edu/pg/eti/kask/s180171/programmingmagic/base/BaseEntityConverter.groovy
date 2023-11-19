package pl.edu.pg.eti.kask.s180171.programmingmagic.base

import jakarta.faces.component.UIComponent
import jakarta.faces.context.FacesContext
import jakarta.faces.convert.Converter

class BaseEntityConverter<S ,T extends BaseEntity> implements Converter<T> {

    S service

    BaseEntityConverter(S service){
        this.service = service
    }

    @Override
    BaseEntity getAsObject(FacesContext context, UIComponent component, String value) {
        value ? service.get(UUID.fromString(value)) : null
    }

    @Override
    String getAsString(FacesContext context, UIComponent component, T value) {
        value?.uuid?.toString() ?: ""
    }
}
