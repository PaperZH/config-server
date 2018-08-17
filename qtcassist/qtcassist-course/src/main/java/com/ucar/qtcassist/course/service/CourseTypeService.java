package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CourseType;

public interface CourseTypeService {
    int deleteByPrimaryKey(Long id);

    int insert(CourseType record);

    int insertSelective(CourseType record);

    CourseType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseType record);

    int updateByPrimaryKey(CourseType record);
}
