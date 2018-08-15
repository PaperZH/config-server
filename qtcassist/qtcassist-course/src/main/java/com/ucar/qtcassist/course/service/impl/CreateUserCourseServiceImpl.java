package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CreateUserCourseMapper;
import com.ucar.qtcassist.course.model.CreateUserCourse;
import com.ucar.qtcassist.course.service.CreateUserCourseService;
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
