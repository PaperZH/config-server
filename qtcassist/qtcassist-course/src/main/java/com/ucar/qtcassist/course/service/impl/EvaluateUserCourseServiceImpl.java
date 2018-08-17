package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.EvaluateUserCourseMapper;
import com.ucar.qtcassist.course.model.EvaluateUserCourse;
import com.ucar.qtcassist.course.service.EvaluateUserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateUserCourseServiceImpl  implements EvaluateUserCourseService {

    @Autowired
    private EvaluateUserCourseMapper evaluateUserCourseMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return evaluateUserCourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(EvaluateUserCourse record) {
        return evaluateUserCourseMapper.insert(record);
    }

    @Override
    public int insertSelective(EvaluateUserCourse record) {
        return evaluateUserCourseMapper.insertSelective(record);
    }

    @Override
    public EvaluateUserCourse selectByPrimaryKey(Long id) {
        return evaluateUserCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(EvaluateUserCourse record) {
        return evaluateUserCourseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(EvaluateUserCourse record) {
        return evaluateUserCourseMapper.updateByPrimaryKey(record);
    }
}
