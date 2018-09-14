package com.ucar.qtcassist.course.service;


import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin")
public interface AdminFeginClient {

    @RequestMapping(value = "/pages/getUserInfoById", method = RequestMethod.GET)
    public String getUserInfoById(@RequestParam("id") Long id);

    @RequestMapping(value="/pages/getStudentInfoById" ,method = RequestMethod.GET)
    public String getStudentInfoById(@RequestParam("id") Long teacherId);
}