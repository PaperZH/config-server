package com.ucar.qtcassist.courseware.service;

import com.ucar.qtcassist.courseware.model.DO.CoursewareTypeDO;

import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月30日
 */
public interface CoursewareTypeService {
    String getType(Long tId);

    List<CoursewareTypeDO> getAllType();
}
