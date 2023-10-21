package pl.edu.pg.eti.kask.s180171.programmingmagic.base

import groovy.transform.TupleConstructor
import jakarta.servlet.http.HttpServletResponse

class HttpRequestException extends Exception{

    final int responseCode
    final String message

    HttpRequestException(int code, String msg){
        responseCode = code
        message = msg
    }

    HttpRequestException(int code){
        HttpRequestException(code, "")
    }

}
