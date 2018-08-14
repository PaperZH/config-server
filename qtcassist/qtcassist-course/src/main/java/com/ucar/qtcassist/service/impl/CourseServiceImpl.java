package com.ucar.qtcassist.service.impl;

import com.ucar.qtcassist.dao.CourseMapper;
import com.ucar.qtcassist.model.Course;
import com.ucar.qtcassist.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course selectByPrimaryKey(Long id) {
        return courseMapper.selectByPrimaryKey(id);
    }
}
