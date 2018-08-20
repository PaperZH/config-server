package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.CollectCourseDO;
import org.springframework.stereotype.Component;

@Component
public interface CollectCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CollectCourseDO record);

    int insertSelective(CollectCourseDO record);

    CollectCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CollectCourseDO record);

    int updateByPrimaryKey(CollectCourseDO record);
}