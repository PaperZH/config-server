package com.ucar.qtcassist.schedule.service;

import com.ucar.qtcassist.api.model.DO.PlanDO;
import com.ucar.qtcassist.schedule.dto.PlanDTO;
import com.ucar.qtcassist.schedule.dto.QueryPlanDTO;

import java.util.List;

public interface PlanService {
    int deleteByPrimaryKey(Long id);

    int insert(PlanDTO record);

    int insertSelective(PlanDTO record);

    PlanDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlanDTO record);

    int updateByPrimaryKey(PlanDTO record);

    List<PlanDO> getPlanList(QueryPlanDTO planDTO);

    int getPlanTotal(QueryPlanDTO planDTO);
}
