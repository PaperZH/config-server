package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CollectCourseMapper;
import com.ucar.qtcassist.api.model.DO.CollectCourseDO;
import com.ucar.qtcassist.course.service.CollectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CollectCourseServiceImpl implements CollectCourseService {

    @Autowired
    private CollectCourseMapper collectCourseMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return collectCourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CollectCourseDO record) {
        return collectCourseMapper.insert(record);
    }

    @Override
    public int insertSelective(CollectCourseDO record) {
        return collectCourseMapper.insertSelective(record);
    }

    @Override
    public CollectCourseDO selectByPrimaryKey(Long id) {
        return collectCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CollectCourseDO record) {
        return collectCourseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CollectCourseDO record) {
        return collectCourseMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Long> selectCourseIdList(Long userId) {
        return collectCourseMapper.selectCourseIdList(userId);
    }

    @Override
    public int deleteListByIdList(Long userId, Long[] courseIds) {
        return collectCourseMapper.deleteListByIdList(userId, courseIds);
    }

    @Override
    public CollectCourseDO getByUserIdAndCourseId(Long userId, Long courseId) {
        return collectCourseMapper.getByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public Integer getTotalByCourseId(Long courseId) {
        return collectCourseMapper.getTotalByCourseId(courseId);
    }

}
