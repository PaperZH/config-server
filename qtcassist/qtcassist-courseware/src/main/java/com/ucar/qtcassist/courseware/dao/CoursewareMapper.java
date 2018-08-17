package com.ucar.qtcassist.courseware.dao;

import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import org.springframework.stereotype.Component;

@Component
public interface CoursewareMapper {
    int deleteByPrimaryKey(Long id);

    long insert(CoursewareDO record);

    int insertSelective(CoursewareDO record);

    CoursewareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursewareDO record);

    int updateByPrimaryKey(CoursewareDO record);
}