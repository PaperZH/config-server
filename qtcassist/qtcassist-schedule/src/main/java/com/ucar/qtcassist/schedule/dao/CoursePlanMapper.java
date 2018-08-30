package com.ucar.qtcassist.schedule.dao;

import com.ucar.qtcassist.api.model.DO.CoursePlanDO;
import org.springframework.stereotype.Component;

@Component
public interface CoursePlanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CoursePlanDO record);

    int insertSelective(CoursePlanDO record);

    CoursePlanDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursePlanDO record);

    int updateByPrimaryKey(CoursePlanDO record);
}