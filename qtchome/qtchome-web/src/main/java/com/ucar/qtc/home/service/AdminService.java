package com.ucar.qtc.home.service;

import com.ucar.qtc.home.utils.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "api-admin")
public interface AdminService {

    /**
     * 获取推荐课程列表
     * @return
     */
    @GetMapping(value = "/pages/getRecCourse")
    public ResponseResult getRecCourse();

}
