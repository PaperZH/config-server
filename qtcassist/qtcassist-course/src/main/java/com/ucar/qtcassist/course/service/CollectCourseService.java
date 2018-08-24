package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.api.model.DO.CollectCourseDO;

import java.util.Date;
import java.util.List;

public interface CollectCourseService {
    int deleteByPrimaryKey(Long id);

    int insert(CollectCourseDO record);

    int insertSelective(CollectCourseDO record);

    CollectCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CollectCourseDO record);

    int updateByPrimaryKey(CollectCourseDO record);

    List<Long> selectCourseIdListByUserId(Long userId);

    List<Long> selectCourseIdListByDate(Long userId,
                                        Date startTime,
                                        Date endTime);

    int deleteListById(Long userId, Long[] courseIds);
}
