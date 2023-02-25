
CREATE TABLE IF NOT EXISTS courses
(
    course_id   IDENTITY,
    course_name VARCHAR(255) NOT NULL,
    course_code VARCHAR(255) NOT NULL,
    class_size  INT          NOT NULL,
    CONSTRAINT pk_courses PRIMARY KEY (course_id)
);

ALTER TABLE courses
    ADD CONSTRAINT IF NOT EXISTS uc_courses_coursecode UNIQUE (course_code);

CREATE TABLE IF NOT EXISTS students
(
    student_id IDENTITY,
    en_full_name VARCHAR(255) NOT NULL,
    ar_full_name VARCHAR(255) NOT NULL,
    email_address VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    CONSTRAINT pk_student PRIMARY KEY (student_id)
);

ALTER TABLE students
    ADD CONSTRAINT IF NOT EXISTS uc_student_en_full_name UNIQUE (en_full_name);
ALTER TABLE students
    ADD CONSTRAINT IF NOT EXISTS uc_student_ar_full_name UNIQUE (ar_full_name);

CREATE TABLE IF NOT EXISTS student_courses
(
    registration_id IDENTITY,
    student_id      BIGINT,
    course_id       BIGINT,
    school_year     INT          NOT NULL,
    semester        VARCHAR(255),
    CONSTRAINT pk_course_registration PRIMARY KEY (registration_id)
);

ALTER TABLE student_courses
    ADD CONSTRAINT IF NOT EXISTS FK_COURSE_REGISTRATION_ON_COURSE FOREIGN KEY (course_id) REFERENCES courses (course_id);

ALTER TABLE student_courses
    ADD CONSTRAINT IF NOT EXISTS FK_COURSE_REGISTRATION_ON_STUDENT FOREIGN KEY (student_id) REFERENCES students;

CREATE UNIQUE INDEX IF NOT EXISTS UK_SOMETHING_TABLE ON student_courses
    (student_id, course_id, school_year, semester);


