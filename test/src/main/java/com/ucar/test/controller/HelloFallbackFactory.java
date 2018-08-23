package com.ucar.test.controller;

import com.alibaba.fastjson.JSON;
import com.ucar.qtcassit.api.model.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description: TODO
 *
 * @author wangyang
 * @since 2018/8/23
 */

@Component
public class HelloFallbackFactory implements FallbackFactory<HelloBackgroundService>{

    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloFallbackFactory.class);

    @Override
    public HelloBackgroundService create(Throwable cause) {
        return new HelloBackgroundService (){

            @Override
            public User hello(String name,Integer age){
                LOGGER.error(name+" "+age);
                return new User();
            }
            @Override
            public String hello(@RequestParam("name") String name){
                LOGGER.error(name);
                return "null";
            }
            @Override
            public String hello(@RequestBody User user){
                LOGGER.error(JSON.toJSONString(user));
                return "null";
            }
        };
    }
}
