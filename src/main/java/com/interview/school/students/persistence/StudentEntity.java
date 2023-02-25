package com.interview.school.students.persistence;

import com.interview.school.registration.persistence.CourseRegistrationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * StudentEntity.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Entity
@Table(name = "STUDENTS")
@Getter
@Setter
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String studentId;

    /*
      Ideally in a production application supporting over two languages (languages are continuously expanding)
      I wouldn't store full names as seperated columns in the same table
      I would instead store the localized full names in their own table with a schema as follows
      id, name, language
      I would keep only one 'full name' in the main entity which would be considered the general name
      probably in English

      Also having names be unique isn't the best idea for a production application, but this is just for demonstration purposes.
     */
    @Column(unique = true)
    private String enFullName;
    @Column(unique = true)
    private String arFullName;
    private String emailAddress;
    private String phone;
    private String address;
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private Set<CourseRegistrationEntity> courses;

}
