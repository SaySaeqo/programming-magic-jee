package pl.edu.pg.eti.kask.s180171.programmingmagic.base

import groovy.transform.TupleConstructor
import jakarta.servlet.http.HttpServletResponse
import jakarta.ws.rs.ClientErrorException
import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response

class HttpRequestException extends WebApplicationException{

    HttpRequestException(int code, String msg){
        super(Response.status(code).entity(msg).build())
    }

    HttpRequestException(int code){
        HttpRequestException(code, "")
    }

}
