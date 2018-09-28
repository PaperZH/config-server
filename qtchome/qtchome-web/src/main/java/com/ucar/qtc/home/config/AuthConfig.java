package com.ucar.qtc.home.config;

import com.ucar.qtc.common.intercepter.AuthIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: train
 * @description: SpringMVC拦截器
 * @author: guodong.zhang
 * @create: 2018-09-27 14:25
 */
@Configuration
@EnableSwagger2
public class AuthConfig extends WebMvcConfigurationSupport {

    @Bean
    public AuthIntercepter authIntercepter() {
        return new AuthIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(authIntercepter());

        // 排除配置
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/frontPage**");
        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }
}

