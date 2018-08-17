package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CourseCourseware;

public interface CourseCoursewareService {
    int deleteByPrimaryKey(Long id);

    int insert(CourseCourseware record);

    int insertSelective(CourseCourseware record);

    CourseCourseware selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseCourseware record);

    int updateByPrimaryKey(CourseCourseware record);
}
