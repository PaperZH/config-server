package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CollectCourseDO;

public interface CollectCourseService {
    int deleteByPrimaryKey(Long id);

    int insert(CollectCourseDO record);

    int insertSelective(CollectCourseDO record);

    CollectCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CollectCourseDO record);

    int updateByPrimaryKey(CollectCourseDO record);
}
