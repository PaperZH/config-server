package com.ucar.qtcassist.schedule.service;

import com.ucar.qtcassist.schedule.model.CoursePlanDO;

public interface CoursePlanService {
    int deleteByPrimaryKey(Long id);

    int insert(CoursePlanDO record);

    int insertSelective(CoursePlanDO record);

    CoursePlanDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursePlanDO record);

    int updateByPrimaryKey(CoursePlanDO record);
}
