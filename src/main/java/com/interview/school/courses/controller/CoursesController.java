package com.interview.school.courses.controller;

import com.interview.school.courses.payload.CourseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
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
import javax.validation.constraints.NotNull;
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

    @PostMapping
    public String createCourse(@RequestBody @Valid final CourseDto course) {
        throw new NotImplementedException();
    }

    @GetMapping("/{courseId}")
    public List<CourseDto> getCourses(@PathVariable String courseId) {
        throw new NotImplementedException();
    }

    @PutMapping("/{courseId}")
    public CourseDto updateCourse(
            @PathVariable String courseId,
            @RequestBody CourseDto courseUpdateDto
    ) {
        throw new NotImplementedException();
    }

    @DeleteMapping("/{courseId}")
    public String deleteCourse(@PathVariable @NotBlank String courseId) {
        throw new NotImplementedException();
    }
}
