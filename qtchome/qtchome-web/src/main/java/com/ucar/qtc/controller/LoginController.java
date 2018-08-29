package com.ucar.qtc.controller;

import com.alibaba.fastjson.JSONObject;
import com.ucar.qtc.common.dto.LoginDTO;
import com.ucar.qtc.service.LoginService;
import com.ucar.qtc.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : pingli.zheng
 * @Description : 登录服务
 * @Date : 8:22 2018/8/16
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    /**
     * 用户登录请求
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseResult getLogin(@RequestBody LoginDTO loginDTO){

        return loginService.login(loginDTO);
    }


    /**
     * 退出登录请求
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ResponseResult getLogOut(@RequestParam String userId){
        System.out.println(userId);
        System.out.println("dao le ");
        return ResponseResult.ok();
    }
}
