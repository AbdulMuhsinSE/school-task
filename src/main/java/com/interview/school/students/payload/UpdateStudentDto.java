package com.interview.school.students.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * UpdateStudentDto.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@JsonIgnoreProperties(ignoreUnknown = false)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class UpdateStudentDto {
    //Validations are removed to allow for partial updates, composite validations could be created but are forgone within the scope of the task.
    private String emailAddress;

    private String phone;

    private String address;
}
