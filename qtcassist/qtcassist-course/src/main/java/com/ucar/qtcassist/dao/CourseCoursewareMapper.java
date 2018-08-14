package com.ucar.qtcassist.dao;

import com.ucar.qtcassist.model.CourseCourseware;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseCoursewareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseCourseware record);

    int insertSelective(CourseCourseware record);

    CourseCourseware selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseCourseware record);

    int updateByPrimaryKey(CourseCourseware record);
}