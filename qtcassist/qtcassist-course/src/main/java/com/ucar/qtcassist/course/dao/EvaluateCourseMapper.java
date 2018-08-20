package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.EvaluateCourseDO;
import org.springframework.stereotype.Component;

@Component
public interface EvaluateCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EvaluateCourseDO record);

    int insertSelective(EvaluateCourseDO record);

    EvaluateCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EvaluateCourseDO record);

    int updateByPrimaryKey(EvaluateCourseDO record);
}