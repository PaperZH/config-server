package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.CreateUserCourseRelationDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CreateUserCourseRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CreateUserCourseRelationDO record);

    int insertSelective(CreateUserCourseRelationDO record);

    CreateUserCourseRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CreateUserCourseRelationDO record);

    int updateByPrimaryKey(CreateUserCourseRelationDO record);

    CreateUserCourseRelationDO selectByCourseId(Long courseId);
}