package com.interview.school.courses;

import com.interview.school.courses.payload.CourseDto;
import com.interview.school.courses.persistence.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * CourseMapper.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    CourseEntity courseDTOToEntity(CourseDto course);

    CourseDto courseEntityToDto(CourseEntity course);
}
