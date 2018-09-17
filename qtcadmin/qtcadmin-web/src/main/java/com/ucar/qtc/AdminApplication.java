package com.ucar.qtc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: cong.zhou01
 * @description: SpringBoot后台管理服务启动类
 * @date: 2018/8/7 10:19
 */
@EnableFeignClients(basePackages = {"com.ucar.qtc"})
@EnableCaching
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.ucar.qtc.admin", "com.ucar.qtc.common"})
@MapperScan("com.ucar.qtc.admin.dao")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
