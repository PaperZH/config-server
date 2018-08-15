package com.ucar.qtcassist.courseware.dao;

import com.ucar.qtcassist.courseware.model.DO.CoursewareTypeDO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CoursewareTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CoursewareTypeDO record);

    int insertSelective(CoursewareTypeDO record);

    CoursewareTypeDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursewareTypeDO record);

    int updateByPrimaryKey(CoursewareTypeDO record);

    List<CoursewareTypeDO> getAllTypes();
}