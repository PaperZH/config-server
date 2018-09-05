package com.ucar.qtc.common.dto.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2018/8/3
 * @Description 跨域设置
 * @since 2018/8/3
 */
@Configuration
public class WebFilterConfig {

    /**
     * 使用spring4.5+的跨域请求设置
     * @return
     */
    @Bean
    public FilterRegistrationBean adminCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration originHeaders = new CorsConfiguration();
        //originHeaders.setAllowedOrigins();
        originHeaders.addAllowedOrigin("*");
        originHeaders.setAllowCredentials(true);
        originHeaders.addAllowedHeader("*");
        originHeaders.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", originHeaders);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        //配置CorsFilter优先级
        bean.setOrder(Integer.MIN_VALUE);
        return bean;
    }
}
