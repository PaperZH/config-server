package com.ucar.qtc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author : pingli.zheng
 * @Description : 登录服务
 * @Date : 8:22 2018/8/16
 */
@RestController
public class LoginController {

    /**
     * 用户登录请求
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String getLogin(){
        return "";
    }


    /**
     * 退出登录请求
     * @return
     */
    @RequestMapping(value = "/logOut",method = RequestMethod.GET)
    public String getLogOut(){

        return "";
    }
}
