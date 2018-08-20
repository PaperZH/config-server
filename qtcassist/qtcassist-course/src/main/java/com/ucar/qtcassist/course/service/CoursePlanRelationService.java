package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CoursePlanRelationDO;

public interface CoursePlanRelationService {
    int deleteByPrimaryKey(Long id);

    int insert(CoursePlanRelationDO record);

    int insertSelective(CoursePlanRelationDO record);

    CoursePlanRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursePlanRelationDO record);

    int updateByPrimaryKey(CoursePlanRelationDO record);
}
