package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CreateUserCourseRelationMapper;
import com.ucar.qtcassist.course.model.CreateUserCourseRelationDO;
import com.ucar.qtcassist.course.service.CreateUserCourseRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserCourseRelationServiceImpl implements CreateUserCourseRelationService {

    @Autowired
    private CreateUserCourseRelationMapper createUserCourseRelationMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return createUserCourseRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CreateUserCourseRelationDO record) {
        return createUserCourseRelationMapper.insert(record);
    }

    @Override
    public int insertSelective(CreateUserCourseRelationDO record) {
        return createUserCourseRelationMapper.insertSelective(record);
    }

    @Override
    public CreateUserCourseRelationDO selectByPrimaryKey(Long id) {
        return createUserCourseRelationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CreateUserCourseRelationDO record) {
        return createUserCourseRelationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CreateUserCourseRelationDO record) {
        return createUserCourseRelationMapper.updateByPrimaryKey(record);
    }

    @Override
    public CreateUserCourseRelationDO selectByCourseId(Long courseId) {
        return createUserCourseRelationMapper.selectByCourseId(courseId);
    }
}
