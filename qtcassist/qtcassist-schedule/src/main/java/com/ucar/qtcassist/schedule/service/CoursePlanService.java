package com.ucar.qtcassist.schedule.service;

import com.ucar.qtcassist.api.model.DO.CoursePlanDO;

public interface CoursePlanService {
    int deleteByPrimaryKey(Long id);

    int insert(CoursePlanDO record);

    int insertSelective(CoursePlanDO record);

    CoursePlanDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursePlanDO record);

    int updateByPrimaryKey(CoursePlanDO record);
}
