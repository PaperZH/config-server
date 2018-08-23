package com.ucar.qtcassist.courseware.dao;

import com.ucar.qtcassist.courseware.model.DO.BaseCoursewareDO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BaseCoursewareMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(BaseCoursewareDO record);

    Long insertSelective(BaseCoursewareDO record);

    BaseCoursewareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseCoursewareDO record);

    int updateByPrimaryKey(BaseCoursewareDO record);

    List<BaseCoursewareDO> getAllBaseCoursewares();

    Long getNewId();
}