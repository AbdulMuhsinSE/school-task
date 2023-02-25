package com.interview.school.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

/**
 * ServiceException.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Getter
public class ServiceException extends RuntimeException{
    protected HttpStatus status;
    protected String errorDescription;
    private Object[] arguments;

    private ServiceException(final HttpStatus status, final String errorDescription, final Exception e) {
        super(e);
        this.status = status;
        this.errorDescription = errorDescription;
        this.arguments = new Object[0];
    }

    private ServiceException(HttpStatus status, String errorDescription, Object... arguments) {
        super(new Exception());
        this.status = status;
        this.errorDescription = errorDescription;
        this.arguments = arguments;
    }

    private ServiceException(HttpStatus status, final Exception e) {
        super(e);
        this.status = status;
        this.errorDescription = e.getMessage();
        this.arguments = new Object[0];
    }

    @Override
    public String getMessage() {
        return String.format("Error [%s] - http status [%s]", MessageFormat.format(errorDescription,arguments), status.toString());
    }

    @Override
    public String toString() {
        return getMessage();
    }

    public static ServiceException badRequest(String errorDescription, Object... arguments) {
        return new ServiceException(HttpStatus.BAD_REQUEST, errorDescription, arguments);
    }

    public static ServiceException badRequest(Exception e) {
        return new ServiceException(HttpStatus.BAD_REQUEST, e);
    }

    public static ServiceException internalError(Exception e) {
        return new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    public static ServiceException notFound(final String resource) {
        return new ServiceException(HttpStatus.NOT_FOUND, "{0} could not be found with the given parameters", resource);
    }

}
