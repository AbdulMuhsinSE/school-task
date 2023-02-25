package com.interview.school.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

/**
 * ApiResponseBuilder.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */

public class ApiResponseBuilder {

    public static <R> ResponseEntity<ApiResponse<R>> ok() {
        return ResponseEntity.ok(successResponse());
    }

    public static <R> ResponseEntity<ApiResponse<R>> ok(final R responseBody) {
        return ResponseEntity.ok(successResponse(responseBody));
    }

    public static <R> ResponseEntity<ApiResponse<R>> created(final String entityPath) throws URISyntaxException {
        return ResponseEntity.created(new URI(entityPath)).body((ApiResponse<R>) successResponse(entityPath));
    }


    public static <R> ResponseEntity<ApiResponse<R>> created(final R responseBody) throws URISyntaxException {
        return ResponseEntity.created(new URI("")).body(successResponse(responseBody));
    }

    public static <R> ResponseEntity<ApiResponse<R>> created(final URI location, final R responseBody) throws URISyntaxException {

        if(Objects.nonNull(location)) {
            return ResponseEntity.created(location).body(successResponse(responseBody));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse(responseBody));

    }

    /**
     * build an internal server error (500) response.
     *
     * @param <R> Generic Object
     *
     * @return ResponseEntity contains Internal Server error Response.
     */
    public static <R> ResponseEntity<ApiResponse<R>> internalServerError() {
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    public static <R> ResponseEntity<ApiResponse<R>> internalServerError(String errorMessage) {
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
    }

    public static <R> ResponseEntity<ApiResponse<R>> badRequest(final String error, final Object errorDetails, final Object[] arguments) {
        return errorResponse(HttpStatus.BAD_REQUEST, error, errorDetails, arguments);
    }

    public static <R> ResponseEntity<ApiResponse<R>> badRequest(final List<String> validationErrors) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse("Validation Errors Found", null, null,
                        validationErrors));
    }

    /**
     * create a response with a success status.
     *
     * @param <R> Generic Object
     *
     * @return Success {@link ApiResponse}
     */
    private static <R> ApiResponse<R> successResponse() {
        return successResponse(null);
    }

    /**
     * create a response with a success status.
     *
     * @param responseBody Response Body
     * @param <R>          Generic Object
     *
     * @return {@link ApiResponse} of Success Api Response
     */
    private static <R> ApiResponse<R> successResponse(final R responseBody) {

        ApiResponse<R> response = new ApiResponse<R>();

        response.setResponseStatus("success");
        response.setDateTime(OffsetDateTime.now());
        response.setResponseBody(responseBody);

        return response;
    }


    public static <R> ResponseEntity<ApiResponse<R>> errorResponse(final HttpStatus status, final String error) {
        return ResponseEntity.status(status).body(errorResponse(error, null));
    }


    public static <R> ResponseEntity<ApiResponse<R>> errorResponse(final HttpStatus status, final String error, final Object errorDetails,
                                                                   final Object[] arguments) {
        return ResponseEntity.status(status).body(errorResponse(error, errorDetails, arguments));
    }



    private static <R> ApiResponse<R> errorResponse(final String errorMsg, final Object[] arguments) {
        return errorResponse(errorMsg, null, arguments);
    }

    private static <R> ApiResponse<R> errorResponse( final String errorMsg, final Object errorDetails,
                                                    final Object[] arguments) {
        return errorResponse(errorMsg, errorDetails, arguments, null);
    }

    private static <R> ApiResponse<R> errorResponse(final String errorMsg, final Object errorDetails,
                                                    final Object[] arguments, final List<String> validationErrors) {

        ApiResponse<R> response = new ApiResponse<R>();

        response.setResponseStatus("failed");
        response.setDateTime(OffsetDateTime.now());
        response.setErrorMessage(MessageFormat.format(errorMsg, arguments));
        response.setErrorDetails(errorDetails);
        response.setValidationErrors(validationErrors);

        return response;
    }


    public static ResponseEntity<Object> generic(final HttpStatus status, final String message) {
        return ResponseEntity.status(status).body(errorResponse(null, message, null, new Object[0]));
    }
}

