package com.ucar.qtcassist.course.service;


import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.course.model.UserDO;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin")
public interface AdminFeginClient {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    Result<UserDO> getUserById(@PathVariable("id") Long id);

}
