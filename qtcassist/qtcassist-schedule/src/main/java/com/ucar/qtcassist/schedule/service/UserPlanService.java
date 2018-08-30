package com.ucar.qtcassist.schedule.service;

import com.ucar.qtcassist.api.model.DO.UserPlanDO;

public interface UserPlanService {
    int deleteByPrimaryKey(Long id);

    int insert(UserPlanDO record);

    int insertSelective(UserPlanDO record);

    UserPlanDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPlanDO record);

    int updateByPrimaryKey(UserPlanDO record);
}
