package com.ucar.qtc.service;

import com.ucar.qtc.common.dto.config.FeignMultipartSupportConfig;
import com.ucar.qtcassist.api.CoursewareApi;
import com.ucar.qtcassist.api.model.BaseCoursewareListDTO;
import com.ucar.qtcassist.api.model.CoursewareTypeDTO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.UploadCoursewareDTO;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月30日
 */
@FeignClient(name = "qtcassist",path = "/course",configuration = FeignMultipartSupportConfig.class)
public interface HCoursewareService  {
    @RequestMapping(value = "/getAllBaseCoursewares", method = RequestMethod.POST)
    Result<List<BaseCoursewareListDTO>> getAllBaseCoursewares();



    @RequestMapping(value = "/uploadCourseware", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //@ResponseBody
    Result uploadCourseware(@RequestPart(value="file") MultipartFile file) throws Exception;

//    @Configuration
//    class MultipartSupportConfig {
//
//        @Autowired
//        private ObjectFactory<HttpMessageConverters> messageConverters;
//
//        @Bean
//        @Primary
//        @Scope("prototype")
//        public Encoder feignEncoder() {
//            return new SpringFormEncoder(new SpringEncoder(messageConverters));
//        }
//
//    }

    @RequestMapping(value = "/getAllType",method = RequestMethod.GET)
    Result<List<CoursewareTypeDTO>> getAllType();
}
