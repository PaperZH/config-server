package com.ucar.qtcassist;

import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessageSessionFactoryBean;
import com.taobao.metamorphosis.client.extension.spring.MetaqTopic;
import com.ucar.qtcassist.courseware.util.MesListener;
import com.zuche.framework.common.SpringApplicationContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Map;

@MapperScan("com.ucar.qtcassist.*.dao")
@ComponentScan("com.ucar.qtcassist")
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class QtcassistApplication {

    public static void main(String[] args) throws Exception{
        ApplicationContext applicationContext = SpringApplication.run(QtcassistApplication.class, args);
        SpringApplicationContext.initApplicationContext(applicationContext);

    }
}
