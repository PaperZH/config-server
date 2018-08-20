package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.PraiseCourseMapper;
import com.ucar.qtcassist.course.model.PraiseCourseDO;
import com.ucar.qtcassist.course.service.PraiseCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PraiseCourseServiceImpl implements PraiseCourseService {

    @Autowired
    private PraiseCourseMapper praiseCourseMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return praiseCourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PraiseCourseDO record) {
        return praiseCourseMapper.insert(record);
    }

    @Override
    public int insertSelective(PraiseCourseDO record) {
        return praiseCourseMapper.insertSelective(record);
    }

    @Override
    public PraiseCourseDO selectByPrimaryKey(Long id) {
        return praiseCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PraiseCourseDO record) {
        return praiseCourseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PraiseCourseDO record) {
        return praiseCourseMapper.updateByPrimaryKey(record);
    }
}
