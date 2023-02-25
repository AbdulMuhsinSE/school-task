package com.interview.school.registration.controller;

import com.interview.school.common.response.ApiResponse;
import com.interview.school.common.response.ApiResponseBuilder;
import com.interview.school.registration.payload.CourseRegistrationDto;
import com.interview.school.registration.payload.UpdateRegistrationDto;
import com.interview.school.registration.persistence.CourseRegistrationEntity;
import com.interview.school.registration.service.CourseRegistrationService;
import com.interview.school.students.payload.StudentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * CourseRegistrationController.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@RestController
@Tag(name = "Course Registration")
@RequestMapping("/registration")
@AllArgsConstructor
@Validated
public class CourseRegistrationController {
    CourseRegistrationService srv;

    @Operation(summary = "Register Student to Course",
            description = "Endpoint that allows a student to be registered to a course through their" +
                    " respective ids"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "successful registration of student, student information is returned",
                    content = @Content(schema = @Schema(implementation = StudentDto.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Validation Error", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Failed to save to DB, either duplicate registration" +
                    "or non-existence of student or course", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ApiResponse<StudentDto>> registerStudentToCourse(@RequestBody @Valid CourseRegistrationDto registrationDetails) {
        return ApiResponseBuilder.ok(srv.register(registrationDetails));
    }

    @Operation(summary = "Update a Course Registration",
            description = "Updates a specific registration, changes the year and/or semester." +
                    " In a production application more could possibly be included, grades, status, comments, etc."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "successful update of course registration, student information is returned",
                    content = @Content(schema = @Schema(implementation = StudentDto.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Validation Error", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Failed to save to DB, registration does not exist", content = @Content)
    })
    @PutMapping({"/{courseRegistrationId}"})
    public ResponseEntity<ApiResponse<StudentDto>> updateCourseRegistrationForStudent(
            @PathVariable @NotBlank String courseRegistrationId, @RequestBody @Valid UpdateRegistrationDto registrationDetails
    ) {
        return ApiResponseBuilder.ok(srv.updateRegistration(courseRegistrationId, registrationDetails));
    }

    @Operation(summary = "Remove a Student from a registered course",
            description = "Removes a student's registration to a given course."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "successful removal of student from course",
                    content = @Content(schema = @Schema(implementation = StudentDto.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Validation Error", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Failed to save to DB, registration does not exist", content = @Content)
    })
    @DeleteMapping("/{courseRegistrationId}")
    public ResponseEntity<ApiResponse<StudentDto>> removeCourseRegistrationFromStudent(
            @PathVariable @NotBlank String courseRegistrationId
    ) {
        return ApiResponseBuilder.ok(srv.deleteRegistration(courseRegistrationId));
    }
}
