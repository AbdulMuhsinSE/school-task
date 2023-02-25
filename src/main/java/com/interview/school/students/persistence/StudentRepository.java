package com.interview.school.students.persistence;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity, String> {
}