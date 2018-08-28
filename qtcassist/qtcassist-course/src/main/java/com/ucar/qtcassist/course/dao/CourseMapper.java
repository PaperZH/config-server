package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.api.model.DO.CourseDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseDO record);

    int insertSelective(CourseDO record);

    CourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseDO record);

    int updateByPrimaryKey(CourseDO record);

    Integer getTotal();

    Integer getTotalById(@Param("ids") List<Long> ids);

    Integer getTotalByCourseName(@Param("ids") List<Long> ids, @Param("courseName") String courseName);

    List<CourseDO> getList(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    List<CourseDO> getListByTime(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    List<CourseDO> getListByPraiseNum(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    List<CourseDO> selectListById(@Param("ids") List<Long> ids,
                                  @Param("startIndex") Integer startIndex,
                                  @Param("pageSize")Integer pageSize);

    List<CourseDO> selectListByCourseName(@Param("ids") List<Long> ids,
                                          @Param("courseName") String courseName,
                                          @Param("startIndex") Integer startIndex,
                                          @Param("pageSize") Integer pageSize);

}