package com.ucar.qtc.admin.dao;

import com.ucar.qtc.admin.domain.UserRelationDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: train
 * @description: 用户关系(sys_user_relation)操作类
 * @author: guodong.zhang01
 * @create: 2018-09-20 17:45
 **/
@Mapper
public interface UserRelationDao {
    public List<UserRelationDO> getUserRelation(UserRelationDO userRelationDO);

    public int saveUserRelation(UserRelationDO userRelationDO);

    public int updateUserRelation(UserRelationDO userRelationDO);

    public int removeUserRelation(Long id);
}
