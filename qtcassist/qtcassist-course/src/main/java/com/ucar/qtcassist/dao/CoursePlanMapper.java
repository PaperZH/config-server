package com.ucar.qtcassist.dao;

import com.ucar.qtcassist.model.CoursePlan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoursePlanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CoursePlan record);

    int insertSelective(CoursePlan record);

    CoursePlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursePlan record);

    int updateByPrimaryKey(CoursePlan record);
}