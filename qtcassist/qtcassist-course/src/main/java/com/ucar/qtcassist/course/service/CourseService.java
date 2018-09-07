package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.DO.QueryDO;
import java.util.List;

public interface CourseService {
    int deleteByPrimaryKey(Long id);

    int insert(CourseDO record);

    int insertSelective(CourseDO record);

    CourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseDO record);

    int updateByPrimaryKey(CourseDO record);

    List<CourseDO> getCourseIdAndCourseName(QueryDO queryDO);

    Integer getTotalByIdListAndCondition(List<Long> ids, QueryDO queryDO);

    List<CourseDO> getList(QueryDO queryDO);

    List<CourseDO> getListByCondition(QueryDO queryDO);

    Integer updatePraiseNum(Long courseId, int change);
}
