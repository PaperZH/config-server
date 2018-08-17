package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CoursePlan;

public interface CoursePlanService {
    int deleteByPrimaryKey(Long id);

    int insert(CoursePlan record);

    int insertSelective(CoursePlan record);

    CoursePlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursePlan record);

    int updateByPrimaryKey(CoursePlan record);
}
