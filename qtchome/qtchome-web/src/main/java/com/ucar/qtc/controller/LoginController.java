package com.ucar.qtc.controller;

import com.alibaba.fastjson.JSONObject;
import com.ucar.qtc.dto.LoginDTO;
import com.ucar.qtc.service.LoginService;
import com.ucar.qtc.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @PostMapping("/login")
    public ResponseResult getLogin( @RequestBody LoginDTO user, HttpServletRequest request, HttpServletResponse response){
        System.out.println(user);
//        loginService.login(user, request, response);
        System.out.println(JSONObject.toJSONString(user));
        return ResponseResult.ok();
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
