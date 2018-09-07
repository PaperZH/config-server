package com.ucar.qtcassist.schedule.service.impl;

import com.ucar.qtcassist.schedule.dao.PlanMapper;
import com.ucar.qtcassist.api.model.DO.PlanDO;
import com.ucar.qtcassist.schedule.dto.PlanDTO;
import com.ucar.qtcassist.schedule.dto.QueryPlanDTO;
import com.ucar.qtcassist.schedule.service.PlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return planMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PlanDTO record) {
        return planMapper.insert(record);
    }

    @Override
    public int insertSelective(PlanDTO record) {
        return planMapper.insertSelective(record);
    }

    @Override
    public PlanDO selectByPrimaryKey(Long id) {
        return planMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PlanDTO record) {
        return planMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PlanDTO record) {
        return planMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<PlanDO> getPlanList(QueryPlanDTO planDTO) {
        return planMapper.queryPlan(planDTO);
    }

    @Override
    public int getPlanTotal(QueryPlanDTO planDTO){
        return planMapper.queryTotal(planDTO);
    }

}
