package com.ucar.qtc.service;

import com.ucar.qtc.common.dto.config.FeignMultipartSupportConfig;
import com.ucar.qtcassist.api.model.BaseCoursewareListDTO;
import com.ucar.qtcassist.api.model.CoursewareTypeDTO;
import com.ucar.qtcassist.api.model.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月30日
 */
@FeignClient(name = "qtcassist",path = "/course")
public interface HCoursewareService  {
    @RequestMapping(value = "/getAllBaseCoursewares", method = RequestMethod.POST)
    Result<List<BaseCoursewareListDTO>> getAllBaseCoursewares();



    @RequestMapping(value = "/uploadCourseware", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result uploadCourseware(@RequestPart(value="file") MultipartFile file) throws Exception;

    @RequestMapping(value = "/getAllType",method = RequestMethod.GET)
    Result<List<CoursewareTypeDTO>> getAllType();
}
