package com.ucar.qtcassist.courseware.service;

import com.ucar.qtcassist.courseware.model.DO.BaseCoursewareDO;
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
     * 返回BaseCoursewareListDTO对象列表
     */
    List<BaseCoursewareListDTO> getAllBaseCoursewares();

    /**
     * 添加课件
     */
    Long addBaseCourseware(BaseCoursewareDO baseCoursewareDO);

}
