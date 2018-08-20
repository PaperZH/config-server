package com.ucar.qtcassist.schedule.service.impl;

import com.ucar.qtcassist.schedule.dao.PlanMapper;
import com.ucar.qtcassist.schedule.model.PlanDO;
import com.ucar.qtcassist.schedule.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return planMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PlanDO record) {
        return planMapper.insert(record);
    }

    @Override
    public int insertSelective(PlanDO record) {
        return planMapper.insertSelective(record);
    }

    @Override
    public PlanDO selectByPrimaryKey(Long id) {
        PlanDO planDO = null;
        planDO = planMapper.selectByPrimaryKey(id);
//        return planMapper.selectByPrimaryKey(id);
        return planDO;
    }

    @Override
    public int updateByPrimaryKeySelective(PlanDO record) {
        return planMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PlanDO record) {
        return planMapper.updateByPrimaryKey(record);
    }
}
