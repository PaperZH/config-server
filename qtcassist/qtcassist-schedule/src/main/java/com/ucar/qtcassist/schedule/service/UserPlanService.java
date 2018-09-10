package com.ucar.qtcassist.schedule.service;

import com.ucar.qtcassist.api.model.DO.UserPlanDO;
import com.ucar.qtcassist.schedule.dto.QueryPlanDTO;
import com.ucar.qtcassist.schedule.dto.UserPlanDTO;
import com.ucar.qtcassist.schedule.dto.UserPlanListDTO;
import com.ucar.qtcassist.schedule.vo.PlanDetailsVO;

import java.util.List;

public interface UserPlanService {
    int deleteByPrimaryKey(Long id);

    int insert(UserPlanDO record);

    int insertSelective(UserPlanListDTO record);

    UserPlanDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPlanDO record);

    int updateByPrimaryKey(UserPlanDO record);

    List<UserPlanDTO> queryUserPlan(QueryPlanDTO planDTO);

    int queryTotal(QueryPlanDTO planDTO);

}
