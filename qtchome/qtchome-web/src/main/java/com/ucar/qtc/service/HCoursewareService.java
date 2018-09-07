package com.ucar.qtc.service;

import com.ucar.qtc.common.dto.config.FeignMultipartSupportConfig;
import com.ucar.qtcassist.api.CoursewareApi;
import com.ucar.qtcassist.api.model.*;
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

import java.io.File;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月30日
 */
@FeignClient(name = "qtcassist",path = "/courseware",configuration = FeignMultipartSupportConfig.class)
public interface HCoursewareService  {
    @RequestMapping(value = "/getAllBaseCoursewares", method = RequestMethod.POST)
    Result<List<BaseCoursewareListDTO>> getAllBaseCoursewares();

    @RequestMapping(value = "/saveCourseware", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result saveCourseware(@RequestPart(value="file") MultipartFile file) throws Exception;

    @RequestMapping(value = "/getAllType",method = RequestMethod.GET)
    Result<List<CoursewareTypeDTO>> getAllType();

    @RequestMapping(value = "/addCourseware", method = RequestMethod.POST)
    Result addCourseware(@RequestParam(value = "id") Long id,@RequestParam(value = "num") Long num);

    @RequestMapping(value = "/uploadCourseware", method = RequestMethod.POST)
    Result uploadCourseware(@RequestBody CourseCoursewareDTO courseCoursewareDTO);


}
