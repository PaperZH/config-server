package com.ucar.qtcassist.api;

import com.ucar.qtcassist.hystrix.HelloFallbackFactory;
import com.ucar.qtcassist.api.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description: TODO
 *
 * @author wangyang
 * @since 2018/8/21
 */

@FeignClient(value = "qtcassist", fallbackFactory = HelloFallbackFactory.class)
public interface DemoApi {

    @GetMapping(value = "/hello2")
    User hello(@RequestHeader("name") String name,@RequestHeader("age") Integer age);

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);


    @PostMapping(value = "/hello3")
    String hello(@RequestBody User user);

}