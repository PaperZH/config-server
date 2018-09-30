package com.ucar.qtcassist;

import com.zuche.framework.common.SpringApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: cong.li
 * @description: Schedule服务启动类
 * @date: 2018/9/29 10:29
 */
@ComponentScan("com.ucar.qtcassist")
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class QtcassistScheduleApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(QtcassistScheduleApplication.class, args);
        SpringApplicationContext.initApplicationContext(applicationContext);
    }
}
