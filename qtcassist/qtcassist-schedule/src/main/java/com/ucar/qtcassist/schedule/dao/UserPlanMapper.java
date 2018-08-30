package com.ucar.qtcassist.schedule.dao;

import com.ucar.qtcassist.api.model.DO.UserPlanDO;
import org.springframework.stereotype.Component;

@Component
public interface UserPlanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserPlanDO record);

    int insertSelective(UserPlanDO record);

    UserPlanDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPlanDO record);

    int updateByPrimaryKey(UserPlanDO record);
}