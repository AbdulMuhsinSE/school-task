package com.interview.school.courses.service;

import com.interview.school.common.exception.ServiceException;
import com.interview.school.courses.CourseMapper;
import com.interview.school.courses.payload.CourseDto;
import com.interview.school.courses.payload.UpdateCourseDto;
import com.interview.school.courses.persistence.CourseEntity;
import com.interview.school.courses.persistence.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * CoursesService.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Service
@Slf4j
@AllArgsConstructor
public class CoursesService {
    private final CourseRepository courseRepository;

    public String createCourse(CourseDto course) {
        CourseEntity courseToSave = CourseMapper.INSTANCE.courseDTOToEntity(course);
        try {
            return courseRepository.save(courseToSave).getCourseId();
        } catch (DataAccessException ex) {
            //Better Error Messaging forgone for the task; but should be implemented.
            throw ServiceException.badRequest(ex);
        }
    }

    public List<CourseDto> getCourses(String courseId) {
        List<CourseDto> courses = new ArrayList<>();
        if(StringUtils.isBlank(courseId)) {
            List<CourseEntity> entities = (List<CourseEntity>) courseRepository.findAll();
            courses.addAll(entities.stream().map(CourseMapper.INSTANCE::courseEntityToDto).toList());
        } else {
            courses.add(
                    CourseMapper.INSTANCE.courseEntityToDto(
                            courseRepository.findById(courseId).orElseThrow(
                                    () -> ServiceException.notFound("Course")
                            )
                    )
            );
        }

        return courses;
    }

    /*  Here we limit the update to course name and course size,
        this is an artificial limitation just so we can actually have some logic.
     */
    public CourseDto updateCourses(String courseId, UpdateCourseDto updatedInformation) {
        CourseEntity courseToUpdate = courseRepository.findById(courseId).orElseThrow(
                () -> ServiceException.badRequest("Update Failed: Course with given id, does not exist.")
        );

        courseToUpdate.setCourseName(updatedInformation.getCourseName());
        courseToUpdate.setClassSize(updatedInformation.getClassSize());
        return CourseMapper.INSTANCE.courseEntityToDto(courseToUpdate);
    }

    public void deleteCourse(String courseId) {
        try {
            courseRepository.deleteById(courseId);
        } catch(EmptyResultDataAccessException ex) {
            throw ServiceException.badRequest("Delete Failed: Course with given id, does not exist.");
        }
    }
}
