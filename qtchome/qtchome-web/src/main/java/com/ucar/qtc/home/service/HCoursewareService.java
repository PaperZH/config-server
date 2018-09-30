package com.ucar.qtc.home.service;

import com.ucar.qtc.home.config.FeignMultipartSupportConfig;
import com.ucar.qtcassist.api.model.*;
import org.springframework.cloud.openfeign.FeignClient;
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
    @RequestMapping(value = "/frontPage/getAllBaseCoursewares", method = RequestMethod.POST)
    Result<List<BaseCoursewareListDTO>> getAllBaseCoursewares();

    @RequestMapping(value = "/uploadCourseware", method = RequestMethod.POST,
    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result uploadCourseware(@RequestPart(value="file") MultipartFile file) throws Exception;

    @RequestMapping(value = "/frontPage/getAllType",method = RequestMethod.GET)
    Result<List<CoursewareTypeDTO>> getAllType();

    @RequestMapping(value = "/addCourseware", method = RequestMethod.POST)
    Result addCourseware(@RequestBody CourseCoursewareDTO courseCoursewareDTO);

    @RequestMapping(value = "/saveCourseware", method = RequestMethod.POST)
    Result saveCourseware(@RequestBody CourseCoursewareDTO courseCoursewareDTO);

    @RequestMapping(value = "/downLoadCourseware" ,method = RequestMethod.GET)
    Result<File> downLoadCourseware(Long baseCoursewareId);

    @RequestMapping(value = "/frontPage/getBaseCourseware", method = RequestMethod.POST)
    Result<BaseCoursewareDTO> getBaseCourseware(@RequestBody Long baseCoursewareId);
}
