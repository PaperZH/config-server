package com.ucar.qtc.service;

import com.ucar.qtc.utils.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(value = "api-admin")
public interface AdminService {

}
