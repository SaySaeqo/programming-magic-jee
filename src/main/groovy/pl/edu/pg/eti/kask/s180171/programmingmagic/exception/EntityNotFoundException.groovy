package pl.edu.pg.eti.kask.s180171.programmingmagic.exception

import jakarta.servlet.http.HttpServletResponse
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.HttpRequestException

class EntityNotFoundException extends HttpRequestException{

    EntityNotFoundException(Class<?> clazz, UUID uuid){
        super(HttpServletResponse.SC_NOT_FOUND, "There is no $clazz.simpleName with uuid $uuid")
    }
}
