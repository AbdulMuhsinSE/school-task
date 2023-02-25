package com.interview.school.courses.persistence;

import com.interview.school.registration.persistence.CourseRegistrationEntity;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * CourseEntity.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */

@Entity
@Table(name = "COURSES")
@Getter
@Setter
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String courseId;
    private String courseName;
    @Column(unique = true)
    private String courseCode;
    private int classSize;
    @OneToMany(mappedBy = "course")
    private Set<CourseRegistrationEntity> studentsTakingCourse;
}
