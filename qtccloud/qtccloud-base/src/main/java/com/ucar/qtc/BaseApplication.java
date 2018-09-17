package com.ucar.qtc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: cong.zhou01
 * @description: SpringBoot启动基础服务
 * @date: 2018/8/7 10:19
 */
@MapperScan(basePackages = {"com.ucar.qtc.*.dao"})
@SpringBootApplication
@EnableSwagger2
public class BaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}
}
