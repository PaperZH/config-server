package com.ucar.qtcassist.service.impl;

import com.ucar.qtcassist.dao.CreateUserCourseMapper;
import com.ucar.qtcassist.model.CreateUserCourse;
import com.ucar.qtcassist.service.CreateUserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserCourseServiceImpl implements CreateUserCourseService {

    @Autowired
    private CreateUserCourseMapper createUserCourseMapper;

    @Override
    public CreateUserCourse selectByCourseId(Long courseId) {
        return createUserCourseMapper.selectByCourseId(courseId);
    }
}
