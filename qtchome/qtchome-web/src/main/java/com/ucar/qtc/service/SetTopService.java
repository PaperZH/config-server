package com.ucar.qtc.service;

import com.ucar.qtc.utils.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient("api-admin")
public interface SetTopService {
    /**
     * 获得banner轮播图
     * @return
     */
    @RequestMapping("/getBanner")
    public ResponseResult getBanner();

    /**
     * 获取要推荐的课程列表
     * @return
     */
    @RequestMapping("/getRecCourse")
    ResponseResult getRecCourse();
}
