package com.ucar.qtc.home.service;

import com.ucar.qtc.home.utils.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("qtcassist")
public interface FileService{

    /**
     * 上传文件，返回url地址
     * @return
     */
    @RequestMapping("/addCoursewareFromLocal")
    ResponseResult upload(MultipartFile file);

    /*
     * 删除文件
     * @param fileUrl
     * @return
    ResponseResult delete(String fileUrl);

    /**
     * 下载文件
     * @param fileUrl
     * @return
     */
    //ResponseResult download(String fileUrl);



}
