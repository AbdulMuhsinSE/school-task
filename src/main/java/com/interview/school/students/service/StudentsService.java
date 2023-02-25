package com.interview.school.students.service;

import com.interview.school.common.exception.ServiceException;
import com.interview.school.courses.persistence.CourseEntity;
import com.interview.school.students.StudentMapper;
import com.interview.school.students.payload.StudentDto;
import com.interview.school.students.payload.UpdateStudentDto;
import com.interview.school.students.persistence.StudentEntity;
import com.interview.school.students.persistence.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * StudentsService.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Service
@Slf4j
@AllArgsConstructor
public class StudentsService {
    StudentRepository studentRepository;
    public String createStudent(StudentDto student) {
        StudentEntity newStudent = StudentMapper.INSTANCE.studentDtoToEntity(student);
        try {
            return studentRepository.save(newStudent).getStudentId();
        } catch (DataAccessException ex) {
            throw ServiceException.badRequest(ex);
        }
    }

    public List<StudentDto> getStudents(String studentId) {
        List<StudentDto> students = new ArrayList<>();
        if(StringUtils.isBlank(studentId)) {
            List<StudentEntity> entities = (List<StudentEntity>) studentRepository.findAll();
            students.addAll(entities.stream().map(StudentMapper.INSTANCE::studentEntityToDto).toList());
        } else {
            students.add(
                    StudentMapper.INSTANCE.studentEntityToDto(
                            studentRepository.findById(studentId).orElseThrow(
                                    () -> ServiceException.notFound("Student")
                            )
                    )
            );
        }
        return students;
    }

    public StudentDto updateStudents(String studentId, UpdateStudentDto studentUpdateDto) {
        StudentEntity studentToUpdate = studentRepository.findById(studentId).orElseThrow(
                () -> ServiceException.badRequest("Update Failed: Student with given id, does not exist")
        );

        //Since this is a patch we expect that users of the api may forgo sending fields they dont want to update which indicates that nulls should be ignored
        if(studentUpdateDto.getAddress() != null) {
            studentToUpdate.setAddress(studentUpdateDto.getAddress());
        }

        if(studentUpdateDto.getPhone() != null) {
            studentToUpdate.setPhone(studentUpdateDto.getPhone());
        }

        if(studentUpdateDto.getEmailAddress() != null) {
            studentToUpdate.setEmailAddress(studentUpdateDto.getEmailAddress());
        }

        return StudentMapper.INSTANCE.studentEntityToDto(studentToUpdate);
    }

    public void deleteStudent(String studentId) {
        try {
            studentRepository.deleteById(studentId);
        } catch (EmptyResultDataAccessException ex) {
            throw ServiceException.badRequest("Delete Failed: Student with given id, does not exist");
        }
    }
}
