package com.ucar.qtcassist.schedule.dao;

import com.ucar.qtcassist.api.model.DO.PlanDO;
import com.ucar.qtcassist.schedule.dto.PlanDTO;
import com.ucar.qtcassist.schedule.dto.QueryPlanDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: cong.li
 * @date: 2018/9/29 14:18
 */
@Component
public interface PlanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlanDTO record);

    int insertSelective(PlanDTO record);

    PlanDO selectByPrimaryKey(Long id);

    List<PlanDO> queryPlan(QueryPlanDTO planDTO);

    int queryTotal(QueryPlanDTO planDTO);

    int updateByPrimaryKeySelective(PlanDTO record);

    int updateByPrimaryKey(PlanDTO record);
}