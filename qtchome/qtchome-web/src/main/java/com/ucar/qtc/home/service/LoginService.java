package com.ucar.qtc.home.service;

import com.ucar.qtc.home.dto.LoginDTO;
import com.ucar.qtc.home.utils.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : pingli.zheng
 * @Description : 远程接口服务
 * @Date : 8:22 2018/8/16
 */

@FeignClient(value = "api-admin")
public interface LoginService {

    /**
     *
     * @param loginDTO
     * @return
     */
    @RequestMapping(value = "/homeLogin")
   public ResponseResult login(LoginDTO loginDTO);

    /**
     * 用户退出.
     * @return
     */
    @RequestMapping(value = "/logOut")
   public ResponseResult logOut();
}
