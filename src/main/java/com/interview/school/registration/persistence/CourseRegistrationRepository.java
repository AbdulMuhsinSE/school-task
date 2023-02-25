package com.interview.school.registration.persistence;

import org.springframework.data.repository.CrudRepository;

public interface CourseRegistrationRepository extends CrudRepository<CourseRegistrationEntity, String> {
}