package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.api.model.DO.EvaluateCourseDO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EvaluateCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EvaluateCourseDO record);

    int insertSelective(EvaluateCourseDO record);

    EvaluateCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EvaluateCourseDO record);

    int updateByPrimaryKey(EvaluateCourseDO record);

    Integer getTotalByCourseId(Long courseId);

    List<EvaluateCourseDO> getListByCourseId(Long courseId);
}