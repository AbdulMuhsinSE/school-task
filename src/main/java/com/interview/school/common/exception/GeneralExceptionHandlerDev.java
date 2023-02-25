package com.interview.school.common.exception;

import com.interview.school.common.response.ApiResponse;
import com.interview.school.common.response.ApiResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GeneralExceptionHandlerDev.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.LOWEST_PRECEDENCE)
@Profile("dev")
public class GeneralExceptionHandlerDev {

    /**
     * Catch all handler to catch all unsupported errors
     * @param ex Exception that was missed by other exception handlers.
     * @return Internal Server Error Message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handle(final Exception ex) {

        log.error("Exception Occurred..", ex);
        return ApiResponseBuilder.internalServerError(ex.getMessage());
    }
}

