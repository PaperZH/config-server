package com.ucar.qtcassist.service.impl;

import com.ucar.qtcassist.dao.CollectUserCourseMapper;
import com.ucar.qtcassist.model.CollectUserCourse;
import com.ucar.qtcassist.service.CollectUserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectUserCourseServiceImpl implements CollectUserCourseService {

    @Autowired
    private CollectUserCourseMapper collectUserCourseMapper;

    @Override
    public int insert(CollectUserCourse collectUserCourse) {
        return collectUserCourseMapper.insert(collectUserCourse);
    }
}
