package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.api.model.DO.CollectCourseDO;
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

    List<Long> selectCourseIdList(@Param("userId") Long userId);

    int deleteListByIdList(@Param("userId") Long userId, @Param("courseIds") Long[] courseIds);

    CollectCourseDO getByUserIdAndCourseId(@Param("userId") Long userId, @Param("courseId") Long courseId);

    Integer getTotalByCourseId(@Param("courseId") Long courseId);
}