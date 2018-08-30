package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CourseCoursewareMapper;
import com.ucar.qtcassist.api.model.DO.CourseCoursewareDO;
import com.ucar.qtcassist.course.service.CourseCoursewareService;
import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseCoursewareServiceImpl implements CourseCoursewareService {
    @Autowired
    private CourseCoursewareMapper courseCoursewareMapper;

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
    public Long getIdByCourseIdAndCoursewareId(Long courseId, Long coursewareId) {
        return courseCoursewareMapper.getIdByCourseIdAndCoursewareId(courseId, coursewareId);
    }
}
