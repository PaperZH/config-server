package com.ucar.qtc.base.controller;

import com.ucar.qtc.base.utils.FileUtils;
import com.ucar.qtc.base.utils.ResponseResult;
import com.ucar.qtc.base.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author cong.zhou01
 * @name FileController
 * @description 文件服务
 * @date 2018-08-16 10:23
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file.filePath}")
    String filePath;

    @Value("${file.pre}")
    String filePre;

    @Value("${file.server}")
    String fileServer;

    /**
     * 上传文件
     * @param file
     * @param key
     * @return
     */
    @PostMapping("upload")
    public ResponseResult upload(MultipartFile file, String key) {
        try {
            if (StringUtils.isBlank(key)) {
                key = StringUtils.generateUUID();
            }
            final String resPath = FileUtils.saveFile(file,file.getBytes(),filePath,key);
            final String url = fileServer + filePre + "/"+resPath;
            return ResponseResult.ok().put("fileUrl", url);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.error("文件上传失败");
        }
    }
}
