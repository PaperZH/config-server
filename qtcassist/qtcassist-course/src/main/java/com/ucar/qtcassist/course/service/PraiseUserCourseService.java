package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.PraiseUserCourse;

public interface PraiseUserCourseService {
    int deleteByPrimaryKey(Long id);

    int insert(PraiseUserCourse record);

    int insertSelective(PraiseUserCourse record);

    PraiseUserCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PraiseUserCourse record);

    int updateByPrimaryKey(PraiseUserCourse record);
}
