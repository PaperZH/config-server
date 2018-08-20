package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.PraiseCourseDO;

public interface PraiseCourseService {
    int deleteByPrimaryKey(Long id);

    int insert(PraiseCourseDO record);

    int insertSelective(PraiseCourseDO record);

    PraiseCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PraiseCourseDO record);

    int updateByPrimaryKey(PraiseCourseDO record);
}
