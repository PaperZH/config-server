package com.ucar.qtcassist;

import com.zuche.framework.common.SpringApplicationContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.ucar.qtcassist.*.dao")
@ComponentScan("com.ucar.qtcassist")
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class QtcassistBaseApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(QtcassistBaseApplication.class, args);
        SpringApplicationContext.initApplicationContext(applicationContext);
    }
}
