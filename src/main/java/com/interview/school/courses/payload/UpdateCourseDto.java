package com.interview.school.courses.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * UpdateCourseDto.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@JsonIgnoreProperties(ignoreUnknown = false)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class UpdateCourseDto {
    @NotBlank
    private String courseName;
    @Range(min = 1, max = 30, message = "Class size must be between 1 and 30")
    private int classSize;
}
