package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.CourseDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseDO record);

    int insertSelective(CourseDO record);

    CourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseDO record);

    int updateByPrimaryKey(CourseDO record);
}