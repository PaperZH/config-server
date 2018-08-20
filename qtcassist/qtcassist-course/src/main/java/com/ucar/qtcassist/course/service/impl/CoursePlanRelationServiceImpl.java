package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CoursePlanRelationMapper;
import com.ucar.qtcassist.course.model.CoursePlanRelationDO;
import com.ucar.qtcassist.course.service.CoursePlanRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursePlanRelationServiceImpl implements CoursePlanRelationService {

    @Autowired
    private CoursePlanRelationMapper coursePlanRelationMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return coursePlanRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CoursePlanRelationDO record) {
        return coursePlanRelationMapper.insert(record);
    }

    @Override
    public int insertSelective(CoursePlanRelationDO record) {
        return coursePlanRelationMapper.insertSelective(record);
    }

    @Override
    public CoursePlanRelationDO selectByPrimaryKey(Long id) {
        return coursePlanRelationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CoursePlanRelationDO record) {
        return coursePlanRelationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CoursePlanRelationDO record) {
        return coursePlanRelationMapper.updateByPrimaryKey(record);
    }
}
