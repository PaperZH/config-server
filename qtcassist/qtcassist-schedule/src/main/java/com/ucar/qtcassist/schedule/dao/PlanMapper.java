package com.ucar.qtcassist.schedule.dao;

import com.ucar.qtcassist.schedule.model.PlanDO;
import org.springframework.stereotype.Component;

@Component
public interface PlanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlanDO record);

    int insertSelective(PlanDO record);

    PlanDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlanDO record);

    int updateByPrimaryKey(PlanDO record);
}