package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.api.model.DO.EvaluateCourseDO;
import java.util.List;

public interface EvaluateCourseService {
    int deleteByPrimaryKey(Long id);

    int insert(EvaluateCourseDO record);

    int insertSelective(EvaluateCourseDO record);

    EvaluateCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EvaluateCourseDO record);

    int updateByPrimaryKey(EvaluateCourseDO record);

    Integer getTotalByCourseId(Long courseId);

    List<EvaluateCourseDO> getListByCourseId(Long courseId);
}
