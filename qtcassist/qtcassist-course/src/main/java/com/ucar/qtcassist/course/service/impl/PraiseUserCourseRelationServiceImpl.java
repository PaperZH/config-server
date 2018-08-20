package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.PraiseUserCourseRelationMapper;
import com.ucar.qtcassist.course.model.PraiseUserCourseRelationDO;
import com.ucar.qtcassist.course.service.PraiseUserCourseRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PraiseUserCourseRelationServiceImpl implements PraiseUserCourseRelationService {

    @Autowired
    private PraiseUserCourseRelationMapper praiseUserCourseRelationMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return praiseUserCourseRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PraiseUserCourseRelationDO record) {
        return praiseUserCourseRelationMapper.insert(record);
    }

    @Override
    public int insertSelective(PraiseUserCourseRelationDO record) {
        return praiseUserCourseRelationMapper.insertSelective(record);
    }

    @Override
    public PraiseUserCourseRelationDO selectByPrimaryKey(Long id) {
        return praiseUserCourseRelationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PraiseUserCourseRelationDO record) {
        return praiseUserCourseRelationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PraiseUserCourseRelationDO record) {
        return praiseUserCourseRelationMapper.updateByPrimaryKey(record);
    }
}
