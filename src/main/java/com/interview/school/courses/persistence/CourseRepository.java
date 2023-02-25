package com.interview.school.courses.persistence;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<CourseEntity, String> {
}