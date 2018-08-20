package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.EvaluateUserCourseRelationMapper;
import com.ucar.qtcassist.course.model.EvaluateUserCourseRelationDO;
import com.ucar.qtcassist.course.service.EvaluateUserCourseRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateUserCourseRelationServiceImpl  implements EvaluateUserCourseRelationService {

    @Autowired
    private EvaluateUserCourseRelationMapper evaluateUserCourseRelationMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return evaluateUserCourseRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(EvaluateUserCourseRelationDO record) {
        return evaluateUserCourseRelationMapper.insert(record);
    }

    @Override
    public int insertSelective(EvaluateUserCourseRelationDO record) {
        return evaluateUserCourseRelationMapper.insertSelective(record);
    }

    @Override
    public EvaluateUserCourseRelationDO selectByPrimaryKey(Long id) {
        return evaluateUserCourseRelationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(EvaluateUserCourseRelationDO record) {
        return evaluateUserCourseRelationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(EvaluateUserCourseRelationDO record) {
        return evaluateUserCourseRelationMapper.updateByPrimaryKey(record);
    }
}
