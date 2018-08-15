package com.ucar.qtcassist.courseware.util;

import com.ucar.qtcassist.courseware.dao.BaseCoursewareMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 对前端页面所选择的课件有效性进行校验
 *
 * @author shijie.xu
 * @since 2018年08月14日
 */
public class IsValidCourseware {
    @Autowired
    BaseCoursewareMapper baseCoursewareMapper;

    public boolean isValid(Long id) {
        if(baseCoursewareMapper.selectByPrimaryKey(id) != null) {
            return true;
        }
        return false;
    }
}
