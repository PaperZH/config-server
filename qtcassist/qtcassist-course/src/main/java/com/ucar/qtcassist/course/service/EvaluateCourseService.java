package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.EvaluateCourseDO;

public interface EvaluateCourseService {
    int deleteByPrimaryKey(Long id);

    int insert(EvaluateCourseDO record);

    int insertSelective(EvaluateCourseDO record);

    EvaluateCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EvaluateCourseDO record);

    int updateByPrimaryKey(EvaluateCourseDO record);
}
