package com.ucar.qtcassist.schedule.service;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Headers("Content-Type:application/json")
@FeignClient("api-admin")
public interface AdminFeginClient {

    @RequestMapping(value="/pages/getStudentInfoById" ,method = RequestMethod.GET)
    public String getStudentInfoById(@RequestParam("id") Long teacherId);

    @RequestMapping(value = "/pages/getUserInfoById", method = RequestMethod.GET)
    String getUserInfoById(@RequestParam("id") Long id);
}
