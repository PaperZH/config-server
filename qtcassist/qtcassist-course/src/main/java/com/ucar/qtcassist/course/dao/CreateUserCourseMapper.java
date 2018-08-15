package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.CreateUserCourse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CreateUserCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CreateUserCourse record);

    int insertSelective(CreateUserCourse record);

    CreateUserCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CreateUserCourse record);

    int updateByPrimaryKey(CreateUserCourse record);

    CreateUserCourse selectByCourseId(Long courseId);
}