package com.ucar.qtcassist.courseware.service;

import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;

/**
 * courseware相关服务
 * @author shijie.xu
 * @since 2018年08月14日
 */
public interface CoursewareService {

    /**
     * 添加课件
     * @param coursewareDO
     * @return long
     * */
    Long addCourseware(CoursewareDO coursewareDO);

}
