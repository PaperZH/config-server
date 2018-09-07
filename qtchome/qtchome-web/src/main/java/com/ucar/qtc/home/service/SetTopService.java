package com.ucar.qtc.home.service;

import com.ucar.qtc.home.utils.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("api-admin")
public interface SetTopService {

    /**
     * 获得banner轮播图
     * @return
     */
    @GetMapping("/pages/getRecBanner")
    public ResponseResult getBanner();

    /**
     * 获取要推荐的课程列表
     * @return
     */
    @RequestMapping("/pages/getRecCourse")
    ResponseResult getRecCourse();
}
