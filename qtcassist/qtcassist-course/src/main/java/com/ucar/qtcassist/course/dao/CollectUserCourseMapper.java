package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.CollectUserCourse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectUserCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CollectUserCourse record);

    int insertSelective(CollectUserCourse record);

    CollectUserCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CollectUserCourse record);

    int updateByPrimaryKey(CollectUserCourse record);
}