package com.ucar.qtcassist.courseware.service;

import com.ucar.qtcassist.courseware.model.DO.BaseCoursewareDO;
import com.ucar.qtcassist.courseware.model.DTO.BaseCoursewareDTO;
import com.ucar.qtcassist.courseware.model.DTO.BaseCoursewareListDTO;

import java.util.List;

/**
 * 课件服务
 *
 * @author shijie.xu
 * @since 2018年08月13日
 */
public interface BaseCoursewareService {
    /**
     * 将从数据库中查询的所有BaseCourseware对象和CoursewareType对象进行拼接
     *
     * @return List<BaseCoursewareListDTO>
     */
    List<BaseCoursewareListDTO> getAllBaseCoursewares();

    /**
     * 添加课件
     *
     * @param baseCoursewareDO
     * @return Long
     */
    Long addBaseCourseware(BaseCoursewareDO baseCoursewareDO);

    /**
     * 判断是否为有效课件
     *
     * @param id
     * @return
     */
    boolean isValid(Long id);

    /**
     * 查询课件信息
     */
    BaseCoursewareDTO getBaseCourseware(Long id);

    /**
     * 获取当前添加字段的id
     */
    Long getNewId();
}
