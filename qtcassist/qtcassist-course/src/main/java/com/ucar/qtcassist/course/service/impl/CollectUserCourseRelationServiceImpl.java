package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.CollectUserCourseRelationMapper;
import com.ucar.qtcassist.course.model.CollectUserCourseRelationDO;
import com.ucar.qtcassist.course.service.CollectUserCourseRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectUserCourseRelationServiceImpl implements CollectUserCourseRelationService {

    @Autowired
    private CollectUserCourseRelationMapper collectUserCourseRelationMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return collectUserCourseRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CollectUserCourseRelationDO collectUserCourse) {
        return collectUserCourseRelationMapper.insert(collectUserCourse);
    }

    @Override
    public int insertSelective(CollectUserCourseRelationDO record) {
        return collectUserCourseRelationMapper.insertSelective(record);
    }

    @Override
    public CollectUserCourseRelationDO selectByPrimaryKey(Long id) {
        return collectUserCourseRelationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CollectUserCourseRelationDO record) {
        return collectUserCourseRelationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CollectUserCourseRelationDO record) {
        return collectUserCourseRelationMapper.updateByPrimaryKey(record);
    }
}
