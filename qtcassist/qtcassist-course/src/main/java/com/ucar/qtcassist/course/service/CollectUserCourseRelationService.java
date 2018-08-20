package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CollectUserCourseRelationDO;

public interface CollectUserCourseRelationService {
    int deleteByPrimaryKey(Long id);

    int insert(CollectUserCourseRelationDO record);

    int insertSelective(CollectUserCourseRelationDO record);

    CollectUserCourseRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CollectUserCourseRelationDO record);

    int updateByPrimaryKey(CollectUserCourseRelationDO record);
}
