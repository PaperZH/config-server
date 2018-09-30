package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.service.UserService;
import com.ucar.qtc.home.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : pingli.zheng
 * @Description : 用户接口
 * @Date : 8:22 2018/8/16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/get/{userId}")
    public ResponseResult getUser(@PathVariable long userId){
        return userService.getUser(userId);
    }

    /**
     * 获取当前登陆用户信息
     * @return
     */
    @RequestMapping(value = "/currentUser",method = RequestMethod.POST)
    public ResponseResult currentUser(){
        return userService.currentUser();
    }

    /**
     * 更新用户信息
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseResult updateUser(@RequestBody Map<String,Object> params){
        return userService.updateUser(params);
    }

    /**
     * 获取用户菜单信息
     * @return
     */
    @RequestMapping(value = "/menu/{userId}")
    public ResponseResult getMenu(@PathVariable long userId) {
        return ResponseResult.ok();
    }
}
