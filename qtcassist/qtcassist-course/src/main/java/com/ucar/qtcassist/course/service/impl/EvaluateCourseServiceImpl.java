package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.EvaluateCourseMapper;
import com.ucar.qtcassist.course.model.EvaluateCourseDO;
import com.ucar.qtcassist.course.service.EvaluateCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateCourseServiceImpl  implements EvaluateCourseService {

    @Autowired
    private EvaluateCourseMapper evaluateCourseMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return evaluateCourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(EvaluateCourseDO record) {
        return evaluateCourseMapper.insert(record);
    }

    @Override
    public int insertSelective(EvaluateCourseDO record) {
        return evaluateCourseMapper.insertSelective(record);
    }

    @Override
    public EvaluateCourseDO selectByPrimaryKey(Long id) {
        return evaluateCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(EvaluateCourseDO record) {
        return evaluateCourseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(EvaluateCourseDO record) {
        return evaluateCourseMapper.updateByPrimaryKey(record);
    }
}
