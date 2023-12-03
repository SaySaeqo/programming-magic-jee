package programmingmagic.base;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class HttpRequestException extends WebApplicationException {
    public HttpRequestException(int code, String msg) {
        super(Response.status(code).entity(msg).build());
    }

    public HttpRequestException(int code) {
        this(code, "");
    }
}


