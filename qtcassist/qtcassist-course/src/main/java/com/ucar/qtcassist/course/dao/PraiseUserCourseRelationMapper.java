package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.PraiseUserCourseRelationDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PraiseUserCourseRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PraiseUserCourseRelationDO record);

    int insertSelective(PraiseUserCourseRelationDO record);

    PraiseUserCourseRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PraiseUserCourseRelationDO record);

    int updateByPrimaryKey(PraiseUserCourseRelationDO record);
}