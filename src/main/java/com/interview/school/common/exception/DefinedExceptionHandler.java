package com.interview.school.common.exception;

import com.interview.school.common.response.ApiResponse;
import com.interview.school.common.response.ApiResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * DefinedExceptionHandler.
 * In an ideal world I wouldn't have a global exception handler
 * I would instead break the handling down into multiple classes
 * to allow for separation of concerns and ordering but for the
 * exercise this should prove sufficient.
 * @author AbdulMuhsin J. Al-Kandari
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class DefinedExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationErrors(MethodArgumentNotValidException ex) {
        log.error("Validation Errors Occurred..", ex);
        List<String> validationErrors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).toList();

        return ApiResponseBuilder.badRequest(validationErrors);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponse<Void>> handleServiceException(final ServiceException ex) {

        log.error("Exception Occurred..", ex);
        return ApiResponseBuilder.errorResponse(ex.getStatus(),  ex.getErrorDescription(), null, ex.getArguments());
    }

}
