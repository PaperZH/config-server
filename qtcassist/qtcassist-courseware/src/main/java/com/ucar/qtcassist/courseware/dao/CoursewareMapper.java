package com.ucar.qtcassist.courseware.dao;

import com.ucar.qtcassist.courseware.model.DO.BackDO;
import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CoursewareMapper {
    int deleteByPrimaryKey(Long id);

    long insert(CoursewareDO record);

    int insertSelective(CoursewareDO record);

    CoursewareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursewareDO record);

    int updateByPrimaryKey(CoursewareDO record);

    List<CoursewareDO> selectByListKey(List<Long> id);

    List<BackDO> selectBack();
}