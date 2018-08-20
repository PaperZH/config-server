package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.CourseDO;
import org.springframework.stereotype.Component;

@Component
public interface CourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseDO record);

    int insertSelective(CourseDO record);

    CourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseDO record);

    int updateByPrimaryKey(CourseDO record);
}