package com.ucar.qtc.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author cong.zhou01
 * @name WebConfig
 * @description TODO
 * @date 2018-08-16 11:29
 */
@Configuration
@Component
public class WebConfig extends WebMvcConfigurerAdapter {
    @Value("${file.filePath}")
    String filePath;

    @Value("${file.pre}")
    String pre;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(pre+"/**").addResourceLocations("file:///"+filePath);
        super.addResourceHandlers(registry);
    }
}
