package com.ucar.qtc.controller;

import com.alibaba.fastjson.JSONObject;
import com.ucar.qtc.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseResult getLogin(@RequestBody Map<String,Object> user){
        System.out.println(JSONObject.toJSONString(user));
        System.out.println(user);
        return ResponseResult.ok();
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
