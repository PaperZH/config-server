package com.ucar.qtcassist.courseware.service.Impl;

import com.ucar.qtcassist.courseware.dao.BaseCoursewareMapper;
import com.ucar.qtcassist.courseware.dao.CoursewareMapper;
import com.ucar.qtcassist.courseware.dao.CoursewareTypeMapper;
import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import com.ucar.qtcassist.courseware.model.DO.CoursewareTypeDO;
import com.ucar.qtcassist.courseware.model.DTO.CoursewareDTO;
import com.ucar.qtcassist.courseware.service.CoursewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月14日
 */
@Service
public class CoursewareServiceImpl implements CoursewareService {
    @Autowired
    private BaseCoursewareMapper baseCoursewareMapper;
    @Autowired
    private CoursewareMapper coursewareMapper;
    @Autowired
    private CoursewareTypeMapper coursewareTypeMapper;

    @Override
    public Long addCourseware(CoursewareDO coursewareDO) {
        Long id = coursewareMapper.insert(coursewareDO);
        if(0 == id) {
            return null;
        }
        return id;
    }

    @Override
    public CoursewareDTO getCourseware(Long coursewareId) {
        CoursewareDO coursewareDO = coursewareMapper.selectByPrimaryKey(coursewareId);
        CoursewareTypeDO coursewareTypeDO = coursewareTypeMapper.selectByPrimaryKey(coursewareDO.getTypeId());
        CoursewareDTO coursewareDTO = new CoursewareDTO();
        coursewareDTO.setDescription(coursewareDO.getCoursewareDescription());
        coursewareDTO.setName(coursewareDO.getCoursewareName());
        coursewareDTO.setType(coursewareTypeDO.getTypeName());

        return coursewareDTO;
    }
}
