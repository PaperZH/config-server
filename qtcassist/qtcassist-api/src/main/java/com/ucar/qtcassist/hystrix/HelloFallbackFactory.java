package com.ucar.qtcassist.hystrix;

import com.alibaba.fastjson.JSON;
import com.ucar.qtcassist.api.DemoApi;
import com.ucar.qtcassist.api.model.User;
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
public class HelloFallbackFactory implements FallbackFactory<DemoApi>{

    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloFallbackFactory.class);

    @Override
    public DemoApi create(Throwable cause) {
        return new DemoApi (){

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
