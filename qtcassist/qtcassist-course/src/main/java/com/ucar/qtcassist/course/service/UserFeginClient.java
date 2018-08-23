package com.ucar.qtcassist.course.service;


import com.ucar.qtcassist.api.model.ResponseResult;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin")
public interface UserFeginClient {
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    ResponseResult getUserById(Long id);
}
