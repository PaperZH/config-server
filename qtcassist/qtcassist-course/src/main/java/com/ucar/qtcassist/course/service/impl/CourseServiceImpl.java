package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CourseMapper;
import com.ucar.qtcassist.api.model.CourseDO;
import com.ucar.qtcassist.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Integer getTotal() {
        return courseMapper.getTotal();
    }

    @Override
    public List<CourseDO> getList(Integer firstIndex, Integer pageSize) {
        return courseMapper.getList(firstIndex, pageSize);
    }

    @Override
    public List<CourseDO> getListByTime(Integer startIndex, Integer pageSize) {
        return courseMapper.getListByTime(startIndex, pageSize);
    }

    @Override
    public List<CourseDO> getListByPraiseNum(Integer startIndex, Integer pageSize) {
        return courseMapper.getListByPraiseNum(startIndex, pageSize);
    }

    @Override
    public List<CourseDO> selectListById(List<Long> ids, Integer startIndex, Integer pageSize) {
        return courseMapper.selectListById(ids, startIndex, pageSize);
    }

    @Override
    public List<CourseDO> selectListByCourseName(List<Long> ids, String courseName, Integer startIndex, Integer pageSize) {
        return courseMapper.selectListByCourseName(ids, courseName, startIndex, pageSize);
    }

}
