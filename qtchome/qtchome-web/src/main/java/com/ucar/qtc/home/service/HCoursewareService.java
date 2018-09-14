package com.ucar.qtc.home.service;

import com.ucar.qtc.home.config.FeignMultipartSupportConfig;
import com.ucar.qtcassist.api.model.BaseCoursewareListDTO;
import com.ucar.qtcassist.api.model.CourseCoursewareDTO;
import com.ucar.qtcassist.api.model.CoursewareTypeDTO;
import com.ucar.qtcassist.api.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
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
    Result addCourseware(@RequestParam(value = "id") Long id, @RequestParam(value = "num") Long num);

    @RequestMapping(value = "/uploadCourseware", method = RequestMethod.POST)
    Result uploadCourseware(@RequestBody CourseCoursewareDTO courseCoursewareDTO);


}
