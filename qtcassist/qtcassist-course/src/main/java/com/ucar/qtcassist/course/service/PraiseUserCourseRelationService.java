package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.PraiseUserCourseRelationDO;

public interface PraiseUserCourseRelationService {
    int deleteByPrimaryKey(Long id);

    int insert(PraiseUserCourseRelationDO record);

    int insertSelective(PraiseUserCourseRelationDO record);

    PraiseUserCourseRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PraiseUserCourseRelationDO record);

    int updateByPrimaryKey(PraiseUserCourseRelationDO record);
}
