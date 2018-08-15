package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.CourseCourseware;
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