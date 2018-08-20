package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CreateUserCourseRelationDO;

public interface CreateUserCourseRelationService {

    int deleteByPrimaryKey(Long id);

    int insert(CreateUserCourseRelationDO record);

    int insertSelective(CreateUserCourseRelationDO record);

    CreateUserCourseRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CreateUserCourseRelationDO record);

    int updateByPrimaryKey(CreateUserCourseRelationDO record);

    CreateUserCourseRelationDO selectByCourseId(Long courseId);

}
