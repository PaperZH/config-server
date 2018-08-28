package com.ucar.qtc.service;

import com.netflix.ribbon.proxy.annotation.Http;
import com.ucar.qtc.dto.LoginDTO;
import com.ucar.qtc.utils.ResponseResult;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author : pingli.zheng
 * @Description : 远程接口服务
 * @Date : 8:22 2018/8/16
 */

@Headers("application/json")
@FeignClient(value = "api-admin")
public interface LoginService {

    /**
     * 登陆用户
     * @param
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginDTO loginDTO, @RequestParam("request") HttpServletRequest request,@RequestParam("response") HttpServletResponse response);

    /**
     * 用户退出.
     * @return
     */
    @RequestMapping(value = "/logOut")
   public ResponseResult logOut();
}
