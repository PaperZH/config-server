package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CollectUserCourse;

public interface CollectUserCourseService {
    int deleteByPrimaryKey(Long id);

    int insert(CollectUserCourse record);

    int insertSelective(CollectUserCourse record);

    CollectUserCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CollectUserCourse record);

    int updateByPrimaryKey(CollectUserCourse record);
}
