package com.interview.school.common.validation;

import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * CourseCode.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Target({
        ElementType.FIELD, ElementType.PARAMETER
})
public @interface CourseCode {
    final String COURSE_CODE_REGEX = "[A-Za-z]{3}[0-9]{3}";
    String message() default "Invalid Course Code: Course codes must follow the following convention" +
            " XXX###";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class CourseCodeValidator implements ConstraintValidator<CourseCode, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if(Strings.isBlank(value)) {
                return false;
            }

            if(value.length() != 6) {
                return false;
            }

            return value.matches(COURSE_CODE_REGEX);
        }
    }
}
