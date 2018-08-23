package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.api.model.CourseDO;

import java.util.List;

public interface CourseService {
    int deleteByPrimaryKey(Long id);

    int insert(CourseDO record);

    int insertSelective(CourseDO record);

    CourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseDO record);

    int updateByPrimaryKey(CourseDO record);

    List<CourseDO> getList(Integer startIndex, Integer pageSize, Long typeId);

    List<CourseDO> selectListById(List<Long> ids, Integer startIndex, Integer pageSize);

    List<CourseDO> selectListByCourseName(List<Long> ids, String courseName, Integer startIndex, Integer pageSize);
}
