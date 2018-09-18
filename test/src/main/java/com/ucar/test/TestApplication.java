package com.ucar.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.ucar.test.dao")
//@ComponentScan("com.ucar")
@EnableDiscoveryClient
@EnableFeignClients(basePackages={"com.ucar.qtcassist.api"})
@EnableHystrix
@EnableHystrixDashboard
@EnableCaching
@SpringBootApplication(scanBasePackages = {"com.ucar.test","com.ucar.qtcassist.hystrix"})
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
