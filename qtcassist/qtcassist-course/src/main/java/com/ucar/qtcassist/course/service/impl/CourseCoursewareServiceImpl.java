package com.ucar.qtcassist.course.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ucar.qtcassist.api.model.AddCoursewareListDTO;
import com.ucar.qtcassist.api.model.AddCoursewarePageListDTO;
import com.ucar.qtcassist.api.model.CoursewareDTO;
import com.ucar.qtcassist.api.model.HasBaseCoursewareDTO;
import com.ucar.qtcassist.api.model.VO.AddCoursewareQueryVO;
import com.ucar.qtcassist.course.dao.CourseCoursewareMapper;
import com.ucar.qtcassist.api.model.DO.CourseCoursewareDO;
import com.ucar.qtcassist.course.service.CourseCoursewareService;
import com.ucar.qtcassist.courseware.service.CoursewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseCoursewareServiceImpl implements CourseCoursewareService {
    @Autowired
    private CourseCoursewareMapper courseCoursewareMapper;

    @Autowired
    private CoursewareService coursewareService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return courseCoursewareMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CourseCoursewareDO record) {
        return courseCoursewareMapper.insert(record);
    }

    @Override
    public int insertSelective(CourseCoursewareDO record) {
        return courseCoursewareMapper.insertSelective(record);
    }

    @Override
    public CourseCoursewareDO selectByPrimaryKey(Long id) {
        return courseCoursewareMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CourseCoursewareDO record) {
        return courseCoursewareMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CourseCoursewareDO record) {
        return courseCoursewareMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Long> getCoursewareIdListByCourseId(Long courseId) {
        return courseCoursewareMapper.getCoursewareIdListByCourseId(courseId);
    }

    @Override
    public AddCoursewarePageListDTO getAddCoursewareList(AddCoursewareQueryVO addCoursewareQueryVO) {
        AddCoursewarePageListDTO addCoursewarePageListDTO = new AddCoursewarePageListDTO();
        Integer pageNo = addCoursewareQueryVO.getPageNo();
        Integer pageSize = addCoursewareQueryVO.getPageSize();
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<AddCoursewareListDTO> addCoursewareList = courseCoursewareMapper.getCoursewareListByCourseId(addCoursewareQueryVO.getCourseId());
        PageInfo pageInfo = new PageInfo<>(addCoursewareList);
        addCoursewarePageListDTO.setCount(pageInfo.getTotal());
        addCoursewarePageListDTO.setAddCoursewareListDTO(addCoursewareList);
        return addCoursewarePageListDTO;
    }

    @Override
    public Long getIdByCourseIdAndCoursewareId(Long courseId, Long coursewareId) {
        return courseCoursewareMapper.getIdByCourseIdAndCoursewareId(courseId, coursewareId);
    }

    @Override
    public Integer hasBasecourseware(HasBaseCoursewareDTO hasBaseCoursewareDTO) {
        return courseCoursewareMapper.hasBasecourseware(hasBaseCoursewareDTO);
    }
}
