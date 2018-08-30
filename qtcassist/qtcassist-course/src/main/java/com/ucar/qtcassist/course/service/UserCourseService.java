package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.api.model.DO.UserCourseDO;

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

    List<Long> selectCourseIdList(Long userId, Date startDate, Date endDate);

    int deleteListByIdList(Long userId, Long[] courseIds);
}
