package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CourseCoursewareRelationDO;

public interface CourseCoursewareRelationService {
    int deleteByPrimaryKey(Long id);

    int insert(CourseCoursewareRelationDO record);

    int insertSelective(CourseCoursewareRelationDO record);

    CourseCoursewareRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseCoursewareRelationDO record);

    int updateByPrimaryKey(CourseCoursewareRelationDO record);
}
