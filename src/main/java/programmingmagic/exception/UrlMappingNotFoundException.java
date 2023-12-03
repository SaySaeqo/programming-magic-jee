package programmingmagic.exception;

import jakarta.servlet.http.HttpServletResponse;
import programmingmagic.base.HttpRequestException;

public class UrlMappingNotFoundException extends HttpRequestException {
    public UrlMappingNotFoundException(String path, String httpMethod) {
        super(HttpServletResponse.SC_NOT_FOUND, "There is no " + httpMethod + " mapping for '" + path + "'");
    }
}


