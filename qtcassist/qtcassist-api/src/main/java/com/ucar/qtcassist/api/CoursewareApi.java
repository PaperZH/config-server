package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.model.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Description: TODO
 *
 * @author
 * @since
 */
public interface CoursewareApi {

    @RequestMapping(value = "/getAllBaseCoursewares", method = RequestMethod.POST)
    Result<List<BaseCoursewareListDTO>> getAllBaseCoursewares();

    @RequestMapping(value = "/uploadCourseware", method = RequestMethod.POST)
    Result uploadCourseware(MultipartFile file) throws Exception;

    @RequestMapping(value = "/getBaseCourseware/{baseCoursewareId}", method = RequestMethod.GET)
    Result getBaseCourseware(@PathVariable("baseCoursewareId") Long baseCoursewareId);

    @RequestMapping(value = "/getAllType",method = RequestMethod.GET)
    Result<List<CoursewareTypeDTO>> getAllType();

    /**
     * 从系统库中选择以后 插入课件表
     * @param
     * @return
     */
    @RequestMapping(value = "/addCourseware", method = RequestMethod.POST)
    Result addCourseware(@RequestParam(value = "id") Long id,@RequestParam(value = "num") Long num);

    @RequestMapping(value = "/saveCourseware", method = RequestMethod.POST)
    Result saveCourseware(@RequestBody CourseCoursewareDTO courseCoursewareDTO);

    @RequestMapping(value = "/queryList" )
    Result<List<BackCoursewareDTO>> queryList(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

}