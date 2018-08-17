package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CreateUserCourse;

public interface CreateUserCourseService {

    int deleteByPrimaryKey(Long id);

    int insert(CreateUserCourse record);

    int insertSelective(CreateUserCourse record);

    CreateUserCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CreateUserCourse record);

    int updateByPrimaryKey(CreateUserCourse record);

    CreateUserCourse selectByCourseId(Long courseId);

}
