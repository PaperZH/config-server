package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.Course;

public interface CourseService {

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}
