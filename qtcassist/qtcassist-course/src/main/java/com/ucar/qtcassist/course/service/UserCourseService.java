package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.UserCourseDO;

import java.util.Date;
import java.util.List;

public interface UserCourseService {
    int deleteByPrimaryKey(Long id);

    int insert(UserCourseDO record);

    int insertSelective(UserCourseDO record);

    UserCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCourseDO record);

    int updateByPrimaryKey(UserCourseDO record);

    UserCourseDO selectByCourseId(Long courseId);

    List<Long> selectCourseIdListByUserId(Long userId);

    List<Long> selectCourseIdListByDate(Long userId,
                                        Date startTime,
                                        Date endTime);

    int deleteListById(Long userId, Long[] courseIds);
}
