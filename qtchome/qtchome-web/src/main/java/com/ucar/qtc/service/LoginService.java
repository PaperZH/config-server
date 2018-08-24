package com.ucar.qtc.service;

import com.ucar.qtc.utils.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Author : pingli.zheng
 * @Description : 远程接口服务
 * @Date : 8:22 2018/8/16
 */

@FeignClient(value = "api-home")
public interface LoginService {

    /**
     * 登陆用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/login")
   public ResponseResult login(Map<String,Object> user);

    /**
     * 用户退出.
     * @return
     */
    @RequestMapping(value = "/logOut")
   public ResponseResult logOut();
}
