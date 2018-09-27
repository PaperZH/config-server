package com.ucar.qtc.home.service;

import com.ucar.qtc.home.utils.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "api-admin")
public interface AdminService {

    /**
     * 获得banner轮播图
     * @return
     */
    @GetMapping("/pages/frontPage/getRecBanner")
    public ResponseResult getBanner();

    /**
     * 获取推荐课程列表
     * @return
     */
    @GetMapping(value = "/pages/frontPage/getRecCourse")
    public ResponseResult getRecCourse();
}