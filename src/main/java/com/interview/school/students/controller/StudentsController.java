package com.interview.school.students.controller;

import com.interview.school.common.response.ApiResponse;
import com.interview.school.common.response.ApiResponseBuilder;
import com.interview.school.students.payload.StudentDto;
import com.interview.school.students.payload.UpdateStudentDto;
import com.interview.school.students.service.StudentsService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URISyntaxException;
import java.util.List;

/**
 * StudentsController.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@RestController
@Tag(name = "Students")
@RequestMapping("/students")
@AllArgsConstructor
@Validated
public class StudentsController {
    private StudentsService srv;

    @Hidden
    @GetMapping("/error")
    public ResponseEntity<ApiResponse<Void>> demonstrateProfilesThroughError() {
        throw new RuntimeException("This is a run time exception. If profile is set to prod " +
                "it will not show");
    }

    @Operation(summary = "Create A New Student",
            description = "Endpoint that allows for the creation of a new student"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201", description = "successful resource creation",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Validation Error", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Failed to save to DB", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createStudent(@RequestBody @Valid final StudentDto student) throws URISyntaxException {
        return ApiResponseBuilder.created(srv.createStudent(student));
    }

    @Operation(summary = "Get Student(s)",
            description = "Endpoint that allows for the retrieval of all students, or a specific student using their id"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "successful retrieval creation",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = StudentDto.class))
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404", description = "Student not found", content = @Content)
    })
    @GetMapping(value={"","/{studentId}"})
    public ResponseEntity<ApiResponse<List<StudentDto>>> getStudents(@PathVariable(required = false) String studentId) {
        return ApiResponseBuilder.ok(srv.getStudents(studentId));
    }

    @Operation(summary = "Update Student Info",
            description = "Endpoint that allows for the updating of a student name, and size."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "successful update",
                    content = @Content(schema = @Schema(implementation = StudentDto.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Validation Error", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Update failed due to non-existence of student", content = @Content)
    })
    @PatchMapping("/{studentId}")
    public ResponseEntity<ApiResponse<StudentDto>> updateStudent(
            @PathVariable String studentId,
            @RequestBody UpdateStudentDto studentUpdateDto
    ) {
        return ApiResponseBuilder.ok(srv.updateStudents(studentId, studentUpdateDto));
    }

    @Operation(summary = "Delete Student",
            description = "Endpoint that allows for the deletion of a student by its ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "successful delete",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Validation Error", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Delete failed due to non-existence of student", content = @Content)
    })
    @DeleteMapping("/{studentId}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable @NotBlank String studentId) {
        srv.deleteStudent(studentId);
        return ApiResponseBuilder.ok();
    }
}
