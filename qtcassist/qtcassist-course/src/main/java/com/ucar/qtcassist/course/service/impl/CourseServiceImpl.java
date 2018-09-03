package com.ucar.qtcassist.course.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ucar.qtcassist.api.model.DO.QueryDO;
import com.ucar.qtcassist.course.dao.CourseMapper;
import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CourseDO record) {
        return courseMapper.insert(record);
    }

    @Override
    public int insertSelective(CourseDO record) {
        return courseMapper.insertSelective(record);
    }

    @Override
    public CourseDO selectByPrimaryKey(Long id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CourseDO record) {
        return courseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CourseDO record) {
        return courseMapper.updateByPrimaryKey(record);
    }

    @Override
    public Integer getTotalByIdListAndCourseName(List<Long> ids, String courseName) {
        return courseMapper.getTotalByIdListAndCourseName(ids, courseName);
    }

//    @Override
//    public List<CourseDO> getList(String courseName, Integer startIndex, Integer pageSize, String orderType){
//        return courseMapper.getList(courseName, startIndex, pageSize, orderType);
//    }

    @Override
    public List<CourseDO> getList(QueryDO queryDO){
        return courseMapper.getList(queryDO);
    }

    @Override
    public List<CourseDO> getListByIds(Long[] ids) {

        return courseMapper.getListByIds(ids);
    }

    @Override
    public List<CourseDO> getListByCondition(QueryDO queryDO) {
        return courseMapper.getListByCondition(queryDO);
    }

//    @Override
//    public List<CourseDO> getListByIdListAndCourseName(List<Long> ids, String courseName, Date startDate, Date endDate, Integer startIndex, Integer pageSize) {
//        return courseMapper.getListByIdListAndCourseName(ids, courseName, startDate, endDate, startIndex, pageSize);
//    }


    @Override
    public Integer updatePraiseNum(Long courseId, int change) {
        return courseMapper.updatePraiseNum(courseId, change);
    }

}
