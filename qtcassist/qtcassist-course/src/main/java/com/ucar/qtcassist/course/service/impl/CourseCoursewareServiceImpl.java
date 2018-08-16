package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CourseCoursewareMapper;
import com.ucar.qtcassist.course.model.CourseCourseware;
import com.ucar.qtcassist.course.service.CourseCoursewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseCoursewareServiceImpl implements CourseCoursewareService {
    @Autowired
    private CourseCoursewareMapper courseCoursewareMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return courseCoursewareMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CourseCourseware record) {
        return courseCoursewareMapper.insert(record);
    }

    @Override
    public int insertSelective(CourseCourseware record) {
        return courseCoursewareMapper.insertSelective(record);
    }

    @Override
    public CourseCourseware selectByPrimaryKey(Long id) {
        return courseCoursewareMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CourseCourseware record) {
        return courseCoursewareMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CourseCourseware record) {
        return courseCoursewareMapper.updateByPrimaryKey(record);
    }
}
