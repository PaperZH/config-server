package com.ucar.qtcassist.schedule.service.impl;

import com.ucar.qtcassist.schedule.dao.UserPlanMapper;
import com.ucar.qtcassist.api.model.DO.UserPlanDO;
import com.ucar.qtcassist.schedule.service.UserPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPlanServiceImpl implements UserPlanService {

    @Autowired
    private UserPlanMapper userPlanMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userPlanMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserPlanDO record) {
        return userPlanMapper.insert(record);
    }

    @Override
    public int insertSelective(UserPlanDO record) {
        return userPlanMapper.insertSelective(record);
    }

    @Override
    public UserPlanDO selectByPrimaryKey(Long id) {
        return userPlanMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserPlanDO record) {
        return userPlanMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserPlanDO record) {
        return userPlanMapper.updateByPrimaryKey(record);
    }
}
