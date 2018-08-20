package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.CourseCoursewareDO;
import org.springframework.stereotype.Component;

@Component
public interface CourseCoursewareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseCoursewareDO record);

    int insertSelective(CourseCoursewareDO record);

    CourseCoursewareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseCoursewareDO record);

    int updateByPrimaryKey(CourseCoursewareDO record);
}