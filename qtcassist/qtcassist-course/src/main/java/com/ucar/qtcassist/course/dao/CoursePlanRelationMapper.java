package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.CoursePlanRelationDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoursePlanRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CoursePlanRelationDO record);

    int insertSelective(CoursePlanRelationDO record);

    CoursePlanRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursePlanRelationDO record);

    int updateByPrimaryKey(CoursePlanRelationDO record);
}