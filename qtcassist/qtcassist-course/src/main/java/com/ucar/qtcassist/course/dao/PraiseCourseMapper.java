package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.api.model.DO.PraiseCourseDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface PraiseCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PraiseCourseDO record);

    int insertSelective(PraiseCourseDO record);

    PraiseCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PraiseCourseDO record);

    int updateByPrimaryKey(PraiseCourseDO record);

    PraiseCourseDO getByUserIdAndCourseId(@Param("userId") Long userId, @Param("courseId") Long courseId);
}