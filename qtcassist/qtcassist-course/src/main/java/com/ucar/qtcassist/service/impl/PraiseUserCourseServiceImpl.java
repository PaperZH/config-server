package com.ucar.qtcassist.service.impl;

import com.ucar.qtcassist.dao.PraiseUserCourseMapper;
import com.ucar.qtcassist.model.PraiseUserCourse;
import com.ucar.qtcassist.service.PraiseUserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PraiseUserCourseServiceImpl implements PraiseUserCourseService {

    @Autowired
    private PraiseUserCourseMapper praiseUserCourseMapper;

    @Override
    public int insert(PraiseUserCourse record) {
        return praiseUserCourseMapper.insert(record);
    }
}
