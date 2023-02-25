package com.interview.school.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;

import java.time.OffsetDateTime;

/**
 * ApiResponse.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@JsonRootName("response")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse<R> {
    private String responseStatus;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dateTime;
    private String errorCode;
    private String errorMessage;
    private R responseBody;
    private List<String> validationErrors;
    private Object errorDetails;
}
