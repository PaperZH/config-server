package com.ucar.qtc.admin.service;

import com.ucar.qtc.admin.domain.UserRelationDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: train
 * @description: 关于对sys_user_relation的service操作方法
 * @author: guodong.zhang01
 * @create: 2018-09-21 08:51
 **/
@Service
public interface UserRelationService {
    public List<UserRelationDO> getUserRelation(Long user1Id, Long user2Id);

    public int saveUserRelation(UserRelationDO userRelationDO);

    public int updateUserRelation(UserRelationDO userRelationDO);

    public int removeUserRelation(Long id);
}
