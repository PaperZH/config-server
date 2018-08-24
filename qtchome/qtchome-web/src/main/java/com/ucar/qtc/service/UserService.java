package com.ucar.qtc.service;

import com.ucar.qtc.utils.ResponseResult;

import java.util.Map;

public interface UserService {

    /**
     * 获取用户信息
     * @return
     */
    ResponseResult getUser(long userId);
    /**
     * 更新用户信息
     * @param map (User user)
     * @return
     */
    ResponseResult updateUser(Map<String,Object> map);

    /**
     * 获取用户菜单信息
     * @param
     * @return
     */
    ResponseResult getUserMenu(long userId);
}
