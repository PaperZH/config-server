package com.ucar.qtc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : pingli.zheng
 * @Description : 用户接口
 * @Date : 8:22 2018/8/16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public  String getUser(){
        return "";
    }
    @RequestMapping(value = "/uodate",method = RequestMethod.POST)
    public String updateUser(){
        return "";
    }
    /**
     * 获取用户菜单信息
     * @return
     */
    @RequestMapping(value = "/menu",method = RequestMethod.GET)
    public String getMenu() {
        return super.toString();
    }
}
