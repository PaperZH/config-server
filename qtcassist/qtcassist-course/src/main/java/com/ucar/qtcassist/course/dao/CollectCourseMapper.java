package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.CollectCourseDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface CollectCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CollectCourseDO record);

    int insertSelective(CollectCourseDO record);

    CollectCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CollectCourseDO record);

    int updateByPrimaryKey(CollectCourseDO record);

    List<Long> selectCourseIdListByUserId(Long userId);

    List<Long> selectCourseIdListByDate(@Param("userId") Long userId,
                            @Param("startTime") Date startTime,
                            @Param("endTime") Date endTime);

    int deleteListById(@Param("userId") Long userId, @Param("courseIds") Long[] courseIds);
}