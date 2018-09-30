package com.ucar.qtcassist.courseware.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ucar.qtcassist.api.model.AddCoursewarePageListDTO;
import com.ucar.qtcassist.api.model.BackCoursewareDTO;
import com.ucar.qtcassist.api.model.BackDTO;
import com.ucar.qtcassist.api.model.CoursewareDTO;
import com.ucar.qtcassist.api.model.VO.AddCoursewareQueryVO;
import com.ucar.qtcassist.api.model.VO.CoursewareQueryVO;
import com.ucar.qtcassist.courseware.constant.FileConstant;
import com.ucar.qtcassist.courseware.dao.BaseCoursewareMapper;
import com.ucar.qtcassist.courseware.dao.CoursewareMapper;
import com.ucar.qtcassist.courseware.dao.CoursewareTypeMapper;
import com.ucar.qtcassist.courseware.model.DO.BackDO;
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
        if(list != null && list.size() > 0) {
            List<CoursewareDO> coursewareDOList = coursewareMapper.selectByListKey(list);
            List<CoursewareDTO> coursewareDTOList = new ArrayList<>();
            for(int i = 0; i < coursewareDOList.size(); i++) {
                BaseCoursewareDO baseCoursewareDO = baseCoursewareMapper.selectByPrimaryKey(coursewareDOList.get(i).getBaseCoursewareId());
                CoursewareDTO coursewareDTO = new CoursewareDTO();
                coursewareDTO.setName(coursewareDOList.get(i).getCoursewareName());
                coursewareDTO.setDescription(coursewareDOList.get(i).getCoursewareDescription());
                coursewareDTO.setId(coursewareDOList.get(i).getId());
                coursewareDTO.setPublishTime(coursewareDOList.get(i).getPublishTime());
                coursewareDTO.setSourceUrl(FileConstant.URLPREFIX+baseCoursewareDO.getSourceUrl());
                coursewareDTO.setPreUrl(FileConstant.URLPREFIX+baseCoursewareDO.getPreviewUrl());
                CoursewareTypeDO coursewareTypeDO = coursewareTypeMapper.selectByPrimaryKey(coursewareDOList.get(i).getTypeId());
                coursewareDTO.setType(coursewareTypeDO.getTypeName());
                coursewareDTO.setNum(coursewareDOList.get(i).getCoursewareNum());
                coursewareDTOList.add(coursewareDTO);
            }
            return coursewareDTOList;
        } else {
            return null;
        }

    }

    @Override
    public BackDTO queryPage(CoursewareQueryVO coursewareQueryVO) {
        Integer pageNo = coursewareQueryVO.getPageNo();
        Integer pageSize = coursewareQueryVO.getPageSize();
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize);
        BackDTO backDTO = new BackDTO();
        List<BackDO> backDoList = coursewareMapper.selectBack(coursewareQueryVO);
        PageInfo pageInfo = new PageInfo<>(backDoList);
        List<BackCoursewareDTO> backCoursewareDTOList = new ArrayList<>();
        for(int i = 0; i < backDoList.size(); i++) {
            BackCoursewareDTO backCoursewareDTO = new BackCoursewareDTO();
            BackDO temBackDO = backDoList.get(i);
            backCoursewareDTO.setBaseCoursewareDescription(temBackDO.getBaseCoursewareDescription());
            backCoursewareDTO.setBaseCoursewareName(temBackDO.getBaseCoursewareName());
            backCoursewareDTO.setCoursewareDescription(temBackDO.getCoursewareDescription());
            backCoursewareDTO.setCoursewareName(temBackDO.getCoursewareName());
            backCoursewareDTO.setCoursewareType(temBackDO.getCoursewareType());
            backCoursewareDTO.setPreUrl(temBackDO.getPreUrl());
            backCoursewareDTO.setSourceUrl(temBackDO.getSourceUrl());
            backCoursewareDTOList.add(backCoursewareDTO);
        }
        backDTO.setBackCoursewareDTOList(backCoursewareDTOList);
        backDTO.setCount(pageInfo.getTotal());
        return backDTO;
    }

}
