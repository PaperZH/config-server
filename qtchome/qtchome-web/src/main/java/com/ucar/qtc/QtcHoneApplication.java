package com.ucar.qtc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


/**
 * @author: cong.zhou01
 * @description: SpringBoot前台服务启动类
 * @date: 2018/8/7 10:19
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class QtcHoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(QtcHoneApplication.class, args);
    }

}
