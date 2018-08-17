package com.ucar.qtc.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Author : pingli.zheng
 * @Description : 远程接口服务
 * @Date : 8:22 2018/8/16
 */

@FeignClient(value = "front-end-service")
public interface LoginService {


    @RequestMapping(value = "/login")
    String  login();
}
