package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CourseTypeMapper;
import com.ucar.qtcassist.course.model.CourseType;
import com.ucar.qtcassist.course.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return courseTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CourseType record) {
        return courseTypeMapper.insert(record);
    }

    @Override
    public int insertSelective(CourseType record) {
        return courseTypeMapper.insertSelective(record);
    }

    @Override
    public CourseType selectByPrimaryKey(Long id) {
        return courseTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CourseType record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(CourseType record) {
        return 0;
    }
}
