package com.ucar.qtcassist.dao;

import com.ucar.qtcassist.model.CourseType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseType record);

    int insertSelective(CourseType record);

    CourseType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseType record);

    int updateByPrimaryKey(CourseType record);
}