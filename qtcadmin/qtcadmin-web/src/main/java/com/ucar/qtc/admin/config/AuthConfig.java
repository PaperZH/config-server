package com.ucar.qtc.admin.config;

import com.ucar.qtc.common.intercepter.AuthIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: cong.zhou01
 * @description: SpringMVC拦截器配置
 * @date: 2018/8/7 10:20
 */
@Configuration
public class AuthConfig extends WebMvcConfigurerAdapter {
    @Bean
    public AuthIntercepter authIntercepter() {
        return new AuthIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(authIntercepter());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/homeLogin**");
        addInterceptor.excludePathPatterns("/test**");
        addInterceptor.excludePathPatterns("/currentUser");
        addInterceptor.excludePathPatterns("/user/currentUser");
        addInterceptor.excludePathPatterns("/user");


        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }
}
