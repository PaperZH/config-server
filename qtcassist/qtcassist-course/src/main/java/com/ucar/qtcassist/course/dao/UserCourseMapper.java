package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.api.model.DO.UserCourseDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface UserCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCourseDO record);

    int insertSelective(UserCourseDO record);

    UserCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCourseDO record);

    int updateByPrimaryKey(UserCourseDO record);

    UserCourseDO selectByCourseId(Long courseId);

    List<Long> selectCourseIdList(@Param("userId") Long userId);

    int deleteListByIdList(@Param("userId") Long userId, @Param("courseIds") Long[] courseIds);
}