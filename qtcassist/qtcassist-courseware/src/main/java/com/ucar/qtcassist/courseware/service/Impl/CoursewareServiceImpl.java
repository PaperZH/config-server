package com.ucar.qtcassist.courseware.service.Impl;

import com.ucar.qtcassist.api.model.CoursewareDTO;
import com.ucar.qtcassist.courseware.dao.BaseCoursewareMapper;
import com.ucar.qtcassist.courseware.dao.CoursewareMapper;
import com.ucar.qtcassist.courseware.dao.CoursewareTypeMapper;
import com.ucar.qtcassist.courseware.model.DO.BaseCoursewareDO;
import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import com.ucar.qtcassist.courseware.model.DO.CoursewareTypeDO;
import com.ucar.qtcassist.courseware.service.CoursewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        //coursewareDTO.setType(coursewareTypeDO.getTypeName());

        return coursewareDTO;
    }

    @Override
    public List<CoursewareDTO> selectCoursewareList(List<Long> list) {
        List<CoursewareDO> coursewareDOList = coursewareMapper.selectByListKey(list);
        List<CoursewareDTO> coursewareDTOList = new ArrayList<>();
        if(list != null && list.size() > 0) {
            for(int i = 0; i < coursewareDOList.size(); i++) {
                BaseCoursewareDO baseCoursewareDO = baseCoursewareMapper.selectByPrimaryKey(coursewareDOList.get(i).getBaseCoursewareId());
                CoursewareDTO coursewareDTO = new CoursewareDTO();
                coursewareDTO.setName(coursewareDOList.get(i).getCoursewareName());
                coursewareDTO.setDescription(coursewareDOList.get(i).getCoursewareDescription());
                coursewareDTO.setId(coursewareDOList.get(i).getId());
                coursewareDTO.setPublishTime(coursewareDOList.get(i).getPublishTime());
                coursewareDTO.setSourceUrl(baseCoursewareDO.getSourceUrl());
                coursewareDTOList.add(coursewareDTO);
            }
            return coursewareDTOList;
        } else {
            return null;
        }

    }
}
