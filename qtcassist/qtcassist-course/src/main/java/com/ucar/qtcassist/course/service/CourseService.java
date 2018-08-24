package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.api.model.CourseDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseService {
    int deleteByPrimaryKey(Long id);

    int insert(CourseDO record);

    int insertSelective(CourseDO record);

    CourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseDO record);

    int updateByPrimaryKey(CourseDO record);

    Integer getTotal();

    List<CourseDO> getList(Integer startIndex, Integer pageSize);

    List<CourseDO> getListByTime(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    List<CourseDO> getListByPraiseNum(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    List<CourseDO> selectListById(List<Long> ids, Integer startIndex, Integer pageSize);

    List<CourseDO> selectListByCourseName(List<Long> ids, String courseName, Integer startIndex, Integer pageSize);
}
