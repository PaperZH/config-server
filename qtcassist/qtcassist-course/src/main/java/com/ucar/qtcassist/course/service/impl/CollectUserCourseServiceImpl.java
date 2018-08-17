package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CollectUserCourseMapper;
import com.ucar.qtcassist.course.model.CollectUserCourse;
import com.ucar.qtcassist.course.service.CollectUserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectUserCourseServiceImpl implements CollectUserCourseService {

    @Autowired
    private CollectUserCourseMapper collectUserCourseMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return collectUserCourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CollectUserCourse collectUserCourse) {
        return collectUserCourseMapper.insert(collectUserCourse);
    }

    @Override
    public int insertSelective(CollectUserCourse record) {
        return collectUserCourseMapper.insertSelective(record);
    }

    @Override
    public CollectUserCourse selectByPrimaryKey(Long id) {
        return collectUserCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CollectUserCourse record) {
        return collectUserCourseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CollectUserCourse record) {
        return collectUserCourseMapper.updateByPrimaryKey(record);
    }
}
