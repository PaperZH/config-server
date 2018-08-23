package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.api.model.CourseDO;
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

    List<CourseDO> getList(@Param("startIndex") Integer startIndex,
                           @Param("pageSize") Integer pageSize, @Param("typeId") Long typeId);

    List<CourseDO> selectListById(@Param("ids") List<Long> ids,
                                  @Param("startIndex") Integer startIndex,
                                  @Param("pageSize")Integer pageSize);

    List<CourseDO> selectListByCourseName(@Param("ids") List<Long> ids,
                                          @Param("courseName") String courseName,
                                          @Param("startIndex") Integer startIndex,
                                          @Param("pageSize") Integer pageSize);

}