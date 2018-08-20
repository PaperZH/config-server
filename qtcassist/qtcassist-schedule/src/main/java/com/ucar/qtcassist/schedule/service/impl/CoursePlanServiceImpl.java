package com.ucar.qtcassist.schedule.service.impl;

import com.ucar.qtcassist.schedule.dao.CoursePlanMapper;
import com.ucar.qtcassist.schedule.model.CoursePlanDO;
import com.ucar.qtcassist.schedule.service.CoursePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursePlanServiceImpl implements CoursePlanService {

    @Autowired
    private CoursePlanMapper coursePlanMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return coursePlanMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CoursePlanDO record) {
        return coursePlanMapper.insert(record);
    }

    @Override
    public int insertSelective(CoursePlanDO record) {
        return coursePlanMapper.insertSelective(record);
    }

    @Override
    public CoursePlanDO selectByPrimaryKey(Long id) {
        return coursePlanMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CoursePlanDO record) {
        return coursePlanMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CoursePlanDO record) {
        return coursePlanMapper.updateByPrimaryKey(record);
    }
}
