package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.course.model.UserCourseDO;
import org.springframework.stereotype.Component;

@Component
public interface UserCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCourseDO record);

    int insertSelective(UserCourseDO record);

    UserCourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCourseDO record);

    int updateByPrimaryKey(UserCourseDO record);

    UserCourseDO selectByCourseId(Long courseId);
}