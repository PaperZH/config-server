package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.DO.QueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CourseService {
    int deleteByPrimaryKey(Long id);

    int insert(CourseDO record);

    int insertSelective(CourseDO record);

    CourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseDO record);

    int updateByPrimaryKey(CourseDO record);

    Integer getTotalByIdListAndCourseName(List<Long> ids, String courseName);

    List<CourseDO> getList(QueryDO queryDO);

//    List<CourseDO> getList(String courseName, Integer startIndex, Integer pageSize, String orderType);
//
//    List<CourseDO> getListByIdListAndCourseName(List<Long> ids, String courseName, Date startDate, Date endDate, Integer startIndex, Integer pageSize);
    List<CourseDO> getListByCondition(QueryDO queryDO);

    Integer updatePraiseNum(Long courseId, int change);
}
