package com.ucar.qtc.home.service;

import com.ucar.qtc.home.utils.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("api-admin")
public interface UserService {

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping("/pages/getUserInfoById")
    ResponseResult getUser(@RequestParam("id") long userId);

    /**
     * 获取当前登陆用户信息
     * @return
     */
    @RequestMapping("/user/front/currentUser")
    ResponseResult currentUser();

    /**
     * 更新用户信息
     * @param map (User user)
     * @return
     */
    @RequestMapping("/pages/updateUserInfo")
    ResponseResult updateUser(Map<String, Object> map);
}
