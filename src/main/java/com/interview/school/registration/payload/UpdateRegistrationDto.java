package com.interview.school.registration.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * UpdateRegistrationDto.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateRegistrationDto {
    @Min(value = 2000)
    private int schoolYear;
    //Valid semesters should probably be in some form of lookup table
    @NotBlank
    private String semester;
}
