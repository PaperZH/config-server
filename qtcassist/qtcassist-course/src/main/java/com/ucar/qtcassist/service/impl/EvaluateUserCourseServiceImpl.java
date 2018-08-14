package com.ucar.qtcassist.service.impl;

import com.ucar.qtcassist.dao.EvaluateUserCourseMapper;
import com.ucar.qtcassist.model.EvaluateUserCourse;
import com.ucar.qtcassist.service.EvaluateUserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateUserCourseServiceImpl  implements EvaluateUserCourseService {

    @Autowired
    private EvaluateUserCourseMapper evaluateUserCourseMapper;

    @Override
    public int insert(EvaluateUserCourse record) {
        return evaluateUserCourseMapper.insert(record);
    }
}
