package com.ucar.qtc.common.config;

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
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = System.getProperty("user.dir") + "/data/tmp";
        //文件最大KB，MB
        factory.setMaxFileSize("30MB");
        //设置总上传数据总大小
        factory.setMaxFileSize("30MB");
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }

}
