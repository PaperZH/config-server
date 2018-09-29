package com.ucar.qtcassist.schedule.dao;

import com.ucar.qtcassist.api.model.DO.CoursePlanDO;
import com.ucar.qtcassist.schedule.dto.CoursePlanDTO;
import com.ucar.qtcassist.schedule.dto.QueryCourseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: cong.li
 * @date: 2018/9/29 14:18
 */
@Component
public interface CoursePlanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CoursePlanDO record);

    int insertSelective(CoursePlanDTO record);

    List<CoursePlanDO> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursePlanDO record);

    int updateByPrimaryKey(CoursePlanDO record);

    List<CoursePlanDO> selectByCourseName(QueryCourseDTO courseDTO);

    int selectTotal(QueryCourseDTO courseDTO);
}