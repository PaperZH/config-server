package com.ucar.qtcassist.dao;

import com.ucar.qtcassist.model.DO.CourseCoursewareRelationDO;

public interface CourseCoursewareRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseCoursewareRelationDO record);

    int insertSelective(CourseCoursewareRelationDO record);

    CourseCoursewareRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseCoursewareRelationDO record);

    int updateByPrimaryKey(CourseCoursewareRelationDO record);
}