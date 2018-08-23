package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CourseTypeDO;

public interface CourseTypeService {
    int deleteByPrimaryKey(Long id);

    int insert(CourseTypeDO record);

    int insertSelective(CourseTypeDO record);

    CourseTypeDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseTypeDO record);

    int updateByPrimaryKey(CourseTypeDO record);

    Long getIdByTypeName(String typeName);
}
