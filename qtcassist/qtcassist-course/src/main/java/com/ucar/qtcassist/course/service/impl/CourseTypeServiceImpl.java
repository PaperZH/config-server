package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CourseTypeMapper;
import com.ucar.qtcassist.api.model.DO.CourseTypeDO;
import com.ucar.qtcassist.course.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return courseTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CourseTypeDO record) {
        return courseTypeMapper.insert(record);
    }

    @Override
    public int insertSelective(CourseTypeDO record) {
        return courseTypeMapper.insertSelective(record);
    }

    @Override
    public CourseTypeDO selectByPrimaryKey(Long id) {
        return courseTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CourseTypeDO record) {
        return courseTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CourseTypeDO record) {
        return courseTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public Long getIdByTypeName(String typeName) {
        return courseTypeMapper.getIdByTypeName(typeName);
    }

    @Override
    public List<CourseTypeDO> getAllCourseType() {
        return courseTypeMapper.getAllCourseType();
    }
}
