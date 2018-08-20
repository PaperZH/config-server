package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CourseDO;

public interface CourseService {
    int deleteByPrimaryKey(Long id);

    int insert(CourseDO record);

    int insertSelective(CourseDO record);

    CourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseDO record);

    int updateByPrimaryKey(CourseDO record);
}
