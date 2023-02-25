package com.interview.school.registration.persistence;

import com.interview.school.courses.persistence.CourseEntity;
import com.interview.school.students.persistence.StudentEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CourseRegistrationEntity.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Entity
@Table(name = "STUDENT_COURSES")
@Getter
@Setter
public class CourseRegistrationEntity {
    /*
        We use an id and not a composite key because a student may register for a course multiple
        times if they fail the course. If we utilized a composite id this approach would not be
        usable and we would instead require an Embedded id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String registrationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STUDENT_ID")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    private CourseEntity course;

    private int schoolYear;

    private String semester;
}
