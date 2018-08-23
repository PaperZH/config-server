package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.UserCourseDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface UserCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCourseDO record);

    int insertSelective(UserCourseDO record);

    UserCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCourseDO record);

    int updateByPrimaryKey(UserCourseDO record);

    UserCourseDO selectByCourseId(Long courseId);

    List<Long> selectCourseIdListByUserId(Long userId);

    List<Long> selectCourseIdListByDate(@Param("userId") Long userId,
                                        @Param("startTime") Date startTime,
                                        @Param("endTime") Date endTime);

    int deleteListById(@Param("userId") Long userId, @Param("courseIds") Long[] courseIds);
}