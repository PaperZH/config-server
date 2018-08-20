package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.CourseCoursewareRelationDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseCoursewareRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseCoursewareRelationDO record);

    int insertSelective(CourseCoursewareRelationDO record);

    CourseCoursewareRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseCoursewareRelationDO record);

    int updateByPrimaryKey(CourseCoursewareRelationDO record);
}