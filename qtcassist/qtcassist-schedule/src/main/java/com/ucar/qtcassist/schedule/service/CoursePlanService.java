package com.ucar.qtcassist.schedule.service;

import com.ucar.qtcassist.api.model.DO.CoursePlanDO;
import com.ucar.qtcassist.schedule.dto.CoursePlanDTO;

import java.util.List;

public interface CoursePlanService {
    int deleteByPrimaryKey(Long id);

    int insert(CoursePlanDO record);

    int insertSelective(CoursePlanDTO record);

    List<CoursePlanDO> selectByPrimaryKey(Long id);

    List<CoursePlanDO> selectByCourseName(String courseName);

    int updateByPrimaryKeySelective(CoursePlanDO record);

    int updateByPrimaryKey(CoursePlanDO record);


}
