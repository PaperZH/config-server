package com.ucar.qtcassist.dao;

import com.ucar.qtcassist.model.DO.CoursewareDO;

public interface CoursewareMapper {
    int deleteByPrimaryKey(Long id);

    long insert(CoursewareDO record);

    int insertSelective(CoursewareDO record);

    CoursewareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursewareDO record);

    int updateByPrimaryKey(CoursewareDO record);
}