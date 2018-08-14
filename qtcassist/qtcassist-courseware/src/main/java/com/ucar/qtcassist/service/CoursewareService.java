package com.ucar.qtcassist.service;

import com.ucar.qtcassist.model.DTO.BaseCoursewareListDTO;

import java.util.List;

/**
 * 课件服务
 *
 * @author shijie.xu
 * @since 2018年08月13日
 */
public interface CoursewareService {
    /**
     * 将从数据库中查询的所有BaseCourseware对象和CoursewareType对象进行拼接
     * 返回BaseCoursewareListDTO对象列表
     * */
    List<BaseCoursewareListDTO> getAllBaseCoursewares();

    /**
     *
     * */
}
