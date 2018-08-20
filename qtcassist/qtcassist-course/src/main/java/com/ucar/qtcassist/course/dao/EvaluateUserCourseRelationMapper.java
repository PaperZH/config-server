package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.EvaluateUserCourseRelationDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvaluateUserCourseRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EvaluateUserCourseRelationDO record);

    int insertSelective(EvaluateUserCourseRelationDO record);

    EvaluateUserCourseRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EvaluateUserCourseRelationDO record);

    int updateByPrimaryKey(EvaluateUserCourseRelationDO record);
}