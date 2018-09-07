package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.utils.ResponseResult;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/get/{userId}")
    public ResponseResult getUser(@PathVariable long userId){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("name" , "lee");
        hashMap.put("nickname","shuaige");
        hashMap.put("email","baidu@qq.com");
        hashMap.put("avatar","static/image/2.jpg");
        return ResponseResult.ok().put("user",hashMap);
    }

    /**
     * 更新用户信息
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseResult updateUser(Map<String,Object> params){
        return ResponseResult.ok();
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
