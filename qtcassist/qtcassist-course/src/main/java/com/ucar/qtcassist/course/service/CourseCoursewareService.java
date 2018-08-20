package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CourseCoursewareDO;

public interface CourseCoursewareService {
    int deleteByPrimaryKey(Long id);

    int insert(CourseCoursewareDO record);

    int insertSelective(CourseCoursewareDO record);

    CourseCoursewareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseCoursewareDO record);

    int updateByPrimaryKey(CourseCoursewareDO record);
}
