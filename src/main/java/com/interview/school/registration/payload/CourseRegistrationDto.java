package com.interview.school.registration.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * CourseRegistrationDto.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseRegistrationDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String registrationId;

    @NotBlank
    private String studentId;
    @NotBlank
    private String courseId;
    //Some more complex validations should probably occur on the two values below\
    @Min(value = 2000)
    private int schoolYear;
    //Valid semesters should probably be in some form of lookup table
    @NotBlank
    private String semester;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String courseCode;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String courseName;
}
