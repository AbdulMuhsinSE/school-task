package com.interview.school.registration.service;

import com.interview.school.common.exception.ServiceException;
import com.interview.school.common.response.ApiResponse;
import com.interview.school.courses.CourseMapper;
import com.interview.school.courses.persistence.CourseEntity;
import com.interview.school.courses.service.CoursesService;
import com.interview.school.registration.payload.CourseRegistrationDto;
import com.interview.school.registration.payload.UpdateRegistrationDto;
import com.interview.school.registration.persistence.CourseRegistrationEntity;
import com.interview.school.registration.persistence.CourseRegistrationRepository;
import com.interview.school.students.StudentMapper;
import com.interview.school.students.payload.StudentDto;
import com.interview.school.students.persistence.StudentEntity;
import com.interview.school.students.service.StudentsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

/**
 * CourseRegistrationService.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Service
@Slf4j
@AllArgsConstructor
public class CourseRegistrationService {
    CourseRegistrationRepository courseRegistrationRepository;
    StudentsService studentsService;
    CoursesService coursesService;

    public StudentDto register(CourseRegistrationDto registrationDetails) {
        StudentEntity student = new StudentEntity();//StudentMapper.INSTANCE.studentDtoToEntity(studentsService.getStudents(registrationDetails.getStudentId()).get(0));
        student.setStudentId(registrationDetails.getStudentId());
        CourseEntity course = new CourseEntity();//CourseMapper.INSTANCE.courseDTOToEntity(coursesService.getCourses(registrationDetails.getCourseId()).get(0));
        course.setCourseId(registrationDetails.getCourseId());

        CourseRegistrationEntity registration = new CourseRegistrationEntity();
        registration.setCourse(course);
        registration.setStudent(student);
        registration.setSemester(registrationDetails.getSemester());
        registration.setSchoolYear(registrationDetails.getSchoolYear());

        try {
            return StudentMapper.INSTANCE.studentEntityToDto(courseRegistrationRepository.save(registration).getStudent());
        } catch (DataIntegrityViolationException ex) {
            throw ServiceException.badRequest(ex.getMessage());
        }

    }

    public StudentDto updateRegistration(String courseRegistrationId, UpdateRegistrationDto registrationDetails) {
        CourseRegistrationEntity courseRegistrationEntity = courseRegistrationRepository.findById(courseRegistrationId).orElseThrow(
                () -> ServiceException.badRequest("Update Failed: Course Registration with given id, does not exist")
        );

        courseRegistrationEntity.setSemester(registrationDetails.getSemester());
        courseRegistrationEntity.setSchoolYear(registrationDetails.getSchoolYear());

        return StudentMapper.INSTANCE.studentEntityToDto(courseRegistrationEntity.getStudent());
    }

    public StudentDto deleteRegistration(String courseRegistrationId) {
        CourseRegistrationEntity cre = courseRegistrationRepository.findById(courseRegistrationId).orElseThrow(
                () -> ServiceException.badRequest("Delete Failed: Course Registration with given id, does not exist")
        );
        StudentEntity student = cre.getStudent();
        courseRegistrationRepository.delete(cre);
        return StudentMapper.INSTANCE.studentEntityToDto(student);
    }
}
