package pl.edu.pg.eti.kask.s180171.programmingmagic.exception

import jakarta.servlet.http.HttpServletResponse
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.HttpRequestException

class UrlMappingNotFoundException extends HttpRequestException{

    UrlMappingNotFoundException(String path, String httpMethod){
        super(HttpServletResponse.SC_NOT_FOUND, "There is no $httpMethod mapping for '$path'")
    }
}
