package com.ucar.qtcassist.courseware.service.Impl;

import com.ucar.qtcassist.courseware.dao.BaseCoursewareMapper;
import com.ucar.qtcassist.courseware.dao.CoursewareMapper;
import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import com.ucar.qtcassist.courseware.model.DTO.FrontCoursewareDTO;
import com.ucar.qtcassist.courseware.service.CoursewareService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月14日
 */
public class CoursewareServiceImpl implements CoursewareService {
    @Autowired
    private BaseCoursewareMapper baseCoursewareMapper;
    @Autowired
    private CoursewareMapper coursewareMapper;

    @Override
    public Long addCourseware(FrontCoursewareDTO frontCoursewareDTO) {
        CoursewareDO coursewareDO = new CoursewareDO();
        coursewareDO.setPublishTime(frontCoursewareDTO.getPublishTime());
        coursewareDO.setCoursewareName(frontCoursewareDTO.getCoursewareName());
        coursewareDO.setCoursewareDescription(frontCoursewareDTO.getCoursewareDescription());
        coursewareDO.setBaseCoursewareId(frontCoursewareDTO.getBaseCoursewareId());
        coursewareDO.setTypeId(frontCoursewareDTO.getTypeId());
        coursewareDO.setUpdateTime(frontCoursewareDTO.getUpdateTime());
        Long id= coursewareMapper.insert(coursewareDO);
        if(0 == id) {
            return null;
        }
        return id;
    }
}
