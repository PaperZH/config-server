package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.EvaluateUserCourseRelationDO;

public interface EvaluateUserCourseRelationService {
    int deleteByPrimaryKey(Long id);

    int insert(EvaluateUserCourseRelationDO record);

    int insertSelective(EvaluateUserCourseRelationDO record);

    EvaluateUserCourseRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EvaluateUserCourseRelationDO record);

    int updateByPrimaryKey(EvaluateUserCourseRelationDO record);
}
