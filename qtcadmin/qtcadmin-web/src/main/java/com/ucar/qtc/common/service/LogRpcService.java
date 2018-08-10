package com.ucar.qtc.common.service;

import com.ucar.qtc.common.dto.LogDO;
import com.ucar.qtc.common.intercepter.FeignIntercepter;
import com.ucar.qtc.common.utils.ResponseResult;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-base", configuration = FeignIntercepter.class)
public interface LogRpcService {
    @Async
    @PostMapping("log/save")
    ResponseResult save(LogDO logDO);
}
