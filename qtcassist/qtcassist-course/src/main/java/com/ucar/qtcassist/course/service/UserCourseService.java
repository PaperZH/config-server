package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.UserCourseDO;

public interface UserCourseService {
    int deleteByPrimaryKey(Long id);

    int insert(UserCourseDO record);

    int insertSelective(UserCourseDO record);

    UserCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCourseDO record);

    int updateByPrimaryKey(UserCourseDO record);

    UserCourseDO selectByCourseId(Long courseId);
}
