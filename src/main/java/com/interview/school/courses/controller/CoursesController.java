package com.interview.school.courses.controller;

import com.interview.school.common.response.ApiResponse;
import com.interview.school.common.response.ApiResponseBuilder;
import com.interview.school.courses.payload.CourseDto;
import com.interview.school.courses.payload.UpdateCourseDto;
import com.interview.school.courses.service.CoursesService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URISyntaxException;
import java.util.List;

/**
 * CoursesController.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@RestController
@Tag(name = "Courses")
@RequestMapping("/courses")
@AllArgsConstructor
@Validated
public class CoursesController {
    private final CoursesService srv;

    @Operation(summary = "Create A New Course",
            description = "Endpoint that allows for the creation of a new course"
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
    public ResponseEntity<ApiResponse<String>> createCourse(@RequestBody @Valid final CourseDto course) throws URISyntaxException {
        return ApiResponseBuilder.created(srv.createCourse(course));
    }

    @Operation(summary = "Get Course(s)",
            description = "Endpoint that allows for the retrieval of all courses, or a specific course using it's id"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "successful retrieval creation",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CourseDto.class))
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404", description = "Course not found", content = @Content)
    })
    @GetMapping(value={"","/{courseId}"})
    public ResponseEntity<ApiResponse<List<CourseDto>>> getCourses(@PathVariable(required = false) String courseId) {
        return ApiResponseBuilder.ok(srv.getCourses(courseId));
    }

    @Operation(summary = "Update Course Info",
            description = "Endpoint that allows for the updating of a course name, and size."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "successful update",
                    content = @Content(schema = @Schema(implementation = CourseDto.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Validation Error", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Update failed due to non-existence of course", content = @Content)
    })
    @PutMapping("/{courseId}")
    public ResponseEntity<ApiResponse<CourseDto>> updateCourse(
            @PathVariable String courseId,
            @RequestBody UpdateCourseDto courseUpdateDto
    ) {
        return ApiResponseBuilder.ok(srv.updateCourses(courseId, courseUpdateDto));
    }

    @Operation(summary = "Delete Course",
            description = "Endpoint that allows for the deletion of a course by its ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "successful delete",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Validation Error", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Delete failed due to non-existence of course", content = @Content)
    })
    @DeleteMapping("/{courseId}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable @NotBlank String courseId) {
        srv.deleteCourse(courseId);
        return ApiResponseBuilder.ok();
    }
}
