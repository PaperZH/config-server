package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.CollectUserCourseRelationDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectUserCourseRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CollectUserCourseRelationDO record);

    int insertSelective(CollectUserCourseRelationDO record);

    CollectUserCourseRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CollectUserCourseRelationDO record);

    int updateByPrimaryKey(CollectUserCourseRelationDO record);
}