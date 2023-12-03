package programmingmagic.exception;

import jakarta.servlet.http.HttpServletResponse;
import programmingmagic.base.HttpRequestException;

import java.util.UUID;

public class EntityNotFoundException extends HttpRequestException {
    public EntityNotFoundException(Class<?> clazz, UUID uuid) {
        super(HttpServletResponse.SC_NOT_FOUND, "There is no " + clazz.getSimpleName() + " with uuid " + uuid);
    }

    public EntityNotFoundException(Class<?> clazz, String parameterName, String parameterValue) {
        super(HttpServletResponse.SC_NOT_FOUND, "There is no " + clazz.getSimpleName() + " with " + parameterName + " " + parameterValue);
    }
}


