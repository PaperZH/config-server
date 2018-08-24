package com.ucar.qtc.controller;

import com.ucar.qtc.utils.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequestMapping("/file")
@RestController
public class FileController {

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload")
    ResponseResult upload(MultipartFile file){
        System.out.println("dao");
        System.out.println(file.getContentType());
        System.out.println(file);
        String url = "http://127.0.0.1:8006/test.jpg";
        return ResponseResult.ok().put("fileUrl",url);
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
