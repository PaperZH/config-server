package com.ucar.qtcassist.courseware.service.Impl;

import com.ucar.qtcassist.courseware.dao.CoursewareTypeMapper;
import com.ucar.qtcassist.courseware.service.CoursewareTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月30日
 */
@Service
public class CoursewareTypeServiceImpl implements CoursewareTypeService {

    @Autowired
    CoursewareTypeMapper coursewareTypeMapper;
    @Override
    public String getType(Long tId) {
        return coursewareTypeMapper.selectByPrimaryKey(tId).getTypeName();
    }
}
