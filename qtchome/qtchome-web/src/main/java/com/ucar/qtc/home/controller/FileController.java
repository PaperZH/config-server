package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.service.FileService;
import com.ucar.qtc.home.service.FileUploadService;
import com.ucar.qtc.home.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequestMapping("/file")
@RestController
public class FileController {
    @Autowired
    FileService fileService;

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult upload(@RequestPart("file") MultipartFile file){
        return fileUploadService.upload(file, "");
    }

    /**
     * 删除文件
     * @param map
     * @return
     */
    @RequestMapping(value = "/delete")
    ResponseResult delete(@RequestBody Map<String, Object> map){
        System.out.println(map.get("fileUrl"));
        return  ResponseResult.ok();
    }

    /**
     * 下载文件
     * @param map
     * @return
     */
    @RequestMapping(value = "download")
    ResponseResult download(@RequestBody Map<String,Object> map){
        System.out.println(map);
        return  ResponseResult.ok();
    }
}
