package com.ucar.qtcassist.course.service;


import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.course.model.UserDO;
import com.ucar.qtcassist.course.model.UserDTO;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin")
public interface AdminFeginClient {

    @RequestMapping(value = "/pages/getUserInfoById", method = RequestMethod.GET)
    String getUserInfoById(@RequestParam("id") Long id);

}
