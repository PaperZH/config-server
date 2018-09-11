package com.ucar.qtc.home.service;


import com.ucar.qtc.home.config.FeignMultipartSupportConfig;
import com.ucar.qtc.home.utils.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cong.zhou01
 * @name FileUploadService
 * @description 上传文件
 * @date 2018-09-05 16:25
 */
@FeignClient(name = "api-base", configuration = FeignMultipartSupportConfig.class)
public interface FileUploadService {

    @RequestMapping(method = RequestMethod.POST,
                    value = "/file/upload",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE ,
                    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseResult upload(@RequestPart(value = "file") MultipartFile file, @RequestParam("key") String key);
}
