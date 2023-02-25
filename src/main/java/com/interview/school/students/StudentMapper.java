package com.interview.school.students;

import com.interview.school.registration.payload.CourseRegistrationDto;
import com.interview.school.registration.persistence.CourseRegistrationEntity;
import com.interview.school.students.payload.StudentDto;
import com.interview.school.students.persistence.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * StudentMapper.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentEntity studentDtoToEntity(StudentDto student);

    @Mapping(source = "courses", target = "registeredCourses", qualifiedByName = "coursesToRegisteredCourses")
    StudentDto studentEntityToDto(StudentEntity student);

    @Named("coursesToRegisteredCourses")
    static List<CourseRegistrationDto> coursesToRegistedCourses(Set<CourseRegistrationEntity> courses) {
        List<CourseRegistrationDto> registeredCourses = new ArrayList<>();
        courses.forEach(course -> {
            CourseRegistrationDto registeredCourse = new CourseRegistrationDto();
            registeredCourse.setCourseName(course.getCourse().getCourseName());
            registeredCourse.setCourseCode(course.getCourse().getCourseCode());
            registeredCourse.setSchoolYear(course.getSchoolYear());
            registeredCourse.setSemester(course.getSemester());
            registeredCourse.setRegistrationId(course.getRegistrationId());
            registeredCourse.setCourseId(course.getCourse().getCourseId());

            registeredCourses.add(registeredCourse);
        });
        return registeredCourses;
    }
}
