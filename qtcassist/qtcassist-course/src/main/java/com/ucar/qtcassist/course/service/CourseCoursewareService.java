package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.api.model.DO.CourseCoursewareDO;

import java.util.List;

public interface CourseCoursewareService {
    int deleteByPrimaryKey(Long id);

    int insert(CourseCoursewareDO record);

    int insertSelective(CourseCoursewareDO record);

    CourseCoursewareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseCoursewareDO record);

    int updateByPrimaryKey(CourseCoursewareDO record);

    List<Long> getCoursewareIdListByCourseId(Long courseId);

    Long getIdByCourseIdAndCoursewareId(Long courseId, Long coursewareId);
}
