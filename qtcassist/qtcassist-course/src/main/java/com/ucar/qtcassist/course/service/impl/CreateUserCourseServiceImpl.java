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
    public int deleteByPrimaryKey(Long id) {
        return createUserCourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CreateUserCourse record) {
        return createUserCourseMapper.insert(record);
    }

    @Override
    public int insertSelective(CreateUserCourse record) {
        return createUserCourseMapper.insertSelective(record);
    }

    @Override
    public CreateUserCourse selectByPrimaryKey(Long id) {
        return createUserCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CreateUserCourse record) {
        return createUserCourseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CreateUserCourse record) {
        return createUserCourseMapper.updateByPrimaryKey(record);
    }

    @Override
    public CreateUserCourse selectByCourseId(Long courseId) {
        return createUserCourseMapper.selectByCourseId(courseId);
    }
}
