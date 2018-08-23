package com.ucar.qtc.controller;

import com.ucar.qtc.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

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
    public  ResponseResult getUser(@PathVariable long userId){
        System.out.println(userId);
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
        System.out.println(params);
        return ResponseResult.ok();
    }

    /**
     * 获取用户菜单信息
     * @return
     */
    @RequestMapping(value = "/menu/{userId}")
    public ResponseResult getMenu(@PathVariable long userId) {
        System.out.println(userId);
        return ResponseResult.ok();
    }
}
