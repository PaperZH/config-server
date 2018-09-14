package com.ucar.qtc.admin.rpc;

import com.ucar.qtc.admin.config.FeignMultipartSupportConfig;
import com.ucar.qtc.common.utils.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cong.zhou01
 * @name FileUploadService
 * @description 上传文件
 * @date 2018-09-05 16:25
 */
@FeignClient(name = "api-base",configuration = FeignMultipartSupportConfig.class)
public interface FileUploadService {

    @RequestMapping(method = RequestMethod.POST,
                    value = "/file/upload",
                    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
                    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseResult upload(@RequestPart(value = "file") MultipartFile file, @RequestParam("key") String key);
}
