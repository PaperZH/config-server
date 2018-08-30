package com.ucar.qtcassist.schedule.service;

import com.ucar.qtcassist.api.model.DO.PlanDO;

public interface PlanService {
    int deleteByPrimaryKey(Long id);

    int insert(PlanDO record);

    int insertSelective(PlanDO record);

    PlanDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlanDO record);

    int updateByPrimaryKey(PlanDO record);
}
