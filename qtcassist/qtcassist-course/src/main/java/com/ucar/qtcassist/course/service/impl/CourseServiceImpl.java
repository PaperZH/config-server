package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CourseMapper;
import com.ucar.qtcassist.course.model.CourseDO;
import com.ucar.qtcassist.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CourseDO record) {
        return courseMapper.insert(record);
    }

    @Override
    public int insertSelective(CourseDO record) {
        return courseMapper.insertSelective(record);
    }

    @Override
    public CourseDO selectByPrimaryKey(Long id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CourseDO record) {
        return courseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CourseDO record) {
        return courseMapper.updateByPrimaryKey(record);
    }
}
