package com.interview.school.students.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.interview.school.courses.payload.CourseDto;
import com.interview.school.registration.payload.CourseRegistrationDto;
import lombok.Data;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * StudentDto.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudentDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String studentId;

    @Size(min = 3)
    private String enFullName;

    @Size(min = 3)
    private String arFullName;

    @NotBlank
    @Email
    private String emailAddress;

    @Size(min = 6, max = 20, message = "Please enter a valid phone number, valid phone numbers are between 6, and 20 digits.")
    private String phone;

    @Size(min = 3, message = "Address too short, minimum of 3 characters expected.")
    private String address;

    private List<CourseRegistrationDto> registeredCourses;
}