package com.ucar.qtc.admin.service.impl;

import com.ucar.qtc.admin.dao.UserRelationDao;
import com.ucar.qtc.admin.domain.UserRelationDO;
import com.ucar.qtc.admin.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: train
 * @description: 关于对sys_user_relation的service实现方法
 * @author: guodong.zhang01
 * @create: 2018-09-21 08:53
 **/
@Service
public class UserRelationServiceImpl implements UserRelationService
{
    @Autowired
    private UserRelationDao userRelationDao;

    @Override
    public List<UserRelationDO> getUserRelation(UserRelationDO userRelationDO) {
        return userRelationDao.getUserRelation(userRelationDO);
    }

    @Override
    public int saveUserRelation(UserRelationDO userRelationDO) {
        return userRelationDao.saveUserRelation(userRelationDO);
    }

    @Override
    public int updateUserRelation(UserRelationDO userRelationDO) {
        return userRelationDao.updateUserRelation(userRelationDO);
    }

    @Override
    public int removeUserRelation(Long id) {
        return removeUserRelation(id);
    }
}
