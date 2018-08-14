package com.ucar.qtcassist.dao;

import com.ucar.qtcassist.model.PraiseUserCourse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PraiseUserCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PraiseUserCourse record);

    int insertSelective(PraiseUserCourse record);

    PraiseUserCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PraiseUserCourse record);

    int updateByPrimaryKey(PraiseUserCourse record);
}