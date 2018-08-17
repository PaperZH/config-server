package com.ucar.qtcassist.course.service;


import com.ucar.qtcassist.base.common.intercepter.FeignIntercepter;
import com.ucar.qtcassist.course.model.UserDO;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin")
public interface UserService {
    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    UserDO get(@PathVariable(value = "id") Long id);
}
