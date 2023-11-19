package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import jakarta.enterprise.context.RequestScoped
import jakarta.faces.convert.FacesConverter
import jakarta.inject.Inject
import jakarta.inject.Named
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseEntityConverter

@FacesConverter(forClass = Programmer.class, managed = true)
class ProgrammerConverter extends BaseEntityConverter<ProgrammerService, Programmer>{

    @Inject
    ProgrammerConverter(ProgrammerService service) {
        super(service)
    }
}//?? CZEMU NIE DZIAŁASZ??
