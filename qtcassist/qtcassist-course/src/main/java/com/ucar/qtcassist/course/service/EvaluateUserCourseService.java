package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.EvaluateUserCourse;

public interface EvaluateUserCourseService {
    int deleteByPrimaryKey(Long id);

    int insert(EvaluateUserCourse record);

    int insertSelective(EvaluateUserCourse record);

    EvaluateUserCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EvaluateUserCourse record);

    int updateByPrimaryKey(EvaluateUserCourse record);
}
