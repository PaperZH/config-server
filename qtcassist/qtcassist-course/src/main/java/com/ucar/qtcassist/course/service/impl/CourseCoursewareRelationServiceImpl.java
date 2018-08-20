package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CourseCoursewareRelationMapper;
import com.ucar.qtcassist.course.model.CourseCoursewareRelationDO;
import com.ucar.qtcassist.course.service.CourseCoursewareRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseCoursewareRelationServiceImpl implements CourseCoursewareRelationService {
    @Autowired
    private CourseCoursewareRelationMapper courseCoursewareRelationMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return courseCoursewareRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CourseCoursewareRelationDO record) {
        return courseCoursewareRelationMapper.insert(record);
    }

    @Override
    public int insertSelective(CourseCoursewareRelationDO record) {
        return courseCoursewareRelationMapper.insertSelective(record);
    }

    @Override
    public CourseCoursewareRelationDO selectByPrimaryKey(Long id) {
        return courseCoursewareRelationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CourseCoursewareRelationDO record) {
        return courseCoursewareRelationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CourseCoursewareRelationDO record) {
        return courseCoursewareRelationMapper.updateByPrimaryKey(record);
    }
}
