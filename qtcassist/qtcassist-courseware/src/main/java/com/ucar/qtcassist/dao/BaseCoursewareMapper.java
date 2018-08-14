package com.ucar.qtcassist.dao;

import com.ucar.qtcassist.model.DO.BaseCoursewareDO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BaseCoursewareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseCoursewareDO record);

    int insertSelective(BaseCoursewareDO record);

    BaseCoursewareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseCoursewareDO record);

    int updateByPrimaryKey(BaseCoursewareDO record);

    List<BaseCoursewareDO> getAllBaseCoursewares();
}