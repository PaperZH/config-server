package com.ucar.qtcassist.dao;

import com.ucar.qtcassist.model.EvaluateUserCourse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvaluateUserCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EvaluateUserCourse record);

    int insertSelective(EvaluateUserCourse record);

    EvaluateUserCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EvaluateUserCourse record);

    int updateByPrimaryKey(EvaluateUserCourse record);
}