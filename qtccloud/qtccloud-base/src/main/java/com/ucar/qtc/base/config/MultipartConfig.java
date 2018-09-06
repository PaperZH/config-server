package com.ucar.qtc.base.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @author cong.zhou01
 * @name MultipartConfig
 * @description 上传文件临时路径
 * @date 2018-08-21 13:35
 */
@Configuration
public class MultipartConfig {

    /**
     * 上传文件临时路径
     * @return
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory configFactory = new MultipartConfigFactory();
        String location = System.getProperty("user.dir") + "/file/base/tmp";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        configFactory.setLocation(location);
        return configFactory.createMultipartConfig();
    }
}
