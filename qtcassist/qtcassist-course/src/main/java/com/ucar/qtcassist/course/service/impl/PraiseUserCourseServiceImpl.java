package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.PraiseUserCourseMapper;
import com.ucar.qtcassist.course.model.PraiseUserCourse;
import com.ucar.qtcassist.course.service.PraiseUserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PraiseUserCourseServiceImpl implements PraiseUserCourseService {

    @Autowired
    private PraiseUserCourseMapper praiseUserCourseMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return praiseUserCourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PraiseUserCourse record) {
        return praiseUserCourseMapper.insert(record);
    }

    @Override
    public int insertSelective(PraiseUserCourse record) {
        return praiseUserCourseMapper.insertSelective(record);
    }

    @Override
    public PraiseUserCourse selectByPrimaryKey(Long id) {
        return praiseUserCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PraiseUserCourse record) {
        return praiseUserCourseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PraiseUserCourse record) {
        return praiseUserCourseMapper.updateByPrimaryKey(record);
    }
}
