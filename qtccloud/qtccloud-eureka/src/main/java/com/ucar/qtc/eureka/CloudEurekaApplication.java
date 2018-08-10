package com.ucar.qtc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: cong.zhou01
 * @description: SpringBoot启动服务注册
 * @date: 2018/8/7 10:19
 */
@EnableEurekaServer
@SpringBootApplication
public class CloudEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudEurekaApplication.class, args);
	}
}
