package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.CourseTypeDO;
import org.springframework.stereotype.Component;

@Component
public interface CourseTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseTypeDO record);

    int insertSelective(CourseTypeDO record);

    CourseTypeDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseTypeDO record);

    int updateByPrimaryKey(CourseTypeDO record);

    Long getIdByTypeName(String typeName);
}