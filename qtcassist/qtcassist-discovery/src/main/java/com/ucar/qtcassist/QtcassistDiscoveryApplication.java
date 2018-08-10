package com.ucar.qtcassist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class QtcassistDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(QtcassistDiscoveryApplication.class, args);
    }
}
