package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.api.model.AddCoursewareListDTO;
import com.ucar.qtcassist.api.model.DO.CourseCoursewareDO;
import com.ucar.qtcassist.api.model.HasBaseCoursewareDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseCoursewareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseCoursewareDO record);

    int insertSelective(CourseCoursewareDO record);

    CourseCoursewareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseCoursewareDO record);

    int updateByPrimaryKey(CourseCoursewareDO record);

    List<Long> getCoursewareIdListByCourseId(Long courseId);

    Long getIdByCourseIdAndCoursewareId(@Param("courseId") Long courseId, @Param("coursewareId") Long coursewareId);

    Integer hasBasecourseware(HasBaseCoursewareDTO hasBaseCoursewareDTO);

    /**
     * 通过课程id查询该课程下的课件信息 根据时间倒序
     * @param courseId
     * @return
     */
    List<AddCoursewareListDTO> getCoursewareListByCourseId(Long courseId);
}