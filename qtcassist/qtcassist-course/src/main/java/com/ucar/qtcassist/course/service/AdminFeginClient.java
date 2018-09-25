package com.ucar.qtcassist.course.service;


import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin")
public interface AdminFeginClient {

    @RequestMapping(value = "/pages/getUserInfoById", method = RequestMethod.GET)
    public String getUserInfoById(@RequestParam("id") Long id);

    @RequestMapping(value = "/pages/getUsersInfoById", method = RequestMethod.GET)
    public String getUsersInfoByIds(@RequestParam("ids") Long[] ids);

    @RequestMapping(value="/pages/getStudentInfoById" ,method = RequestMethod.GET)
    public String getStudentInfoById(@RequestParam("id") Long teacherId);
}