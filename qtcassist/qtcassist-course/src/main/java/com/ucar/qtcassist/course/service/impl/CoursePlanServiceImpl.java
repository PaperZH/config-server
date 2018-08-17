package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CoursePlanMapper;
import com.ucar.qtcassist.course.model.CoursePlan;
import com.ucar.qtcassist.course.service.CoursePlanService;
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
    public int insert(CoursePlan record) {
        return coursePlanMapper.insert(record);
    }

    @Override
    public int insertSelective(CoursePlan record) {
        return coursePlanMapper.insertSelective(record);
    }

    @Override
    public CoursePlan selectByPrimaryKey(Long id) {
        return coursePlanMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CoursePlan record) {
        return coursePlanMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CoursePlan record) {
        return coursePlanMapper.updateByPrimaryKey(record);
    }
}
