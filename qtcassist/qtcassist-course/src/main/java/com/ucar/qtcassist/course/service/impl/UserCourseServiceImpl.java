package com.ucar.qtcassist.course.service.impl;

import com.ucar.qtcassist.course.dao.UserCourseMapper;
import com.ucar.qtcassist.api.model.DO.UserCourseDO;
import com.ucar.qtcassist.course.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userCourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserCourseDO record) {
        return userCourseMapper.insert(record);
    }

    @Override
    public int insertSelective(UserCourseDO record) {
        return userCourseMapper.insertSelective(record);
    }

    @Override
    public UserCourseDO selectByPrimaryKey(Long id) {
        return userCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserCourseDO record) {
        return userCourseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserCourseDO record) {
        return userCourseMapper.updateByPrimaryKey(record);
    }

    @Override
    public UserCourseDO selectByCourseId(Long courseId) {
        return userCourseMapper.selectByCourseId(courseId);
    }

    @Override
    public List<Long> selectCourseIdList(Long userId) {
        return userCourseMapper.selectCourseIdList(userId);
    }

    @Override
    public int deleteListByIdList(Long userId, Long[] courseIds) {
        return userCourseMapper.deleteListByIdList(userId, courseIds);
    }
}
