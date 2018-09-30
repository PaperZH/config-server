package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.api.model.AddCoursewarePageListDTO;
import com.ucar.qtcassist.api.model.DO.CourseCoursewareDO;
import com.ucar.qtcassist.api.model.HasBaseCoursewareDTO;
import com.ucar.qtcassist.api.model.VO.AddCoursewareQueryVO;

import java.util.List;

public interface CourseCoursewareService {
    int deleteByPrimaryKey(Long id);

    int insert(CourseCoursewareDO record);

    int insertSelective(CourseCoursewareDO record);

    CourseCoursewareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseCoursewareDO record);

    int updateByPrimaryKey(CourseCoursewareDO record);

    List<Long> getCoursewareIdListByCourseId(Long courseId);

    /**
     * 添加课件页面的，当前课程下所有课件的列表
     * @param addCoursewareQueryVO
     * @return AddCoursewarePageListDTO
     */
    AddCoursewarePageListDTO getAddCoursewareList(AddCoursewareQueryVO addCoursewareQueryVO);

    Long getIdByCourseIdAndCoursewareId(Long courseId, Long coursewareId);

    /**
     * 用来判断选择的课件（BaseCourseware）是否重复
     * @param hasBaseCoursewareDTO
     * @return Integer
     */
    Integer hasBasecourseware(HasBaseCoursewareDTO hasBaseCoursewareDTO);
}
