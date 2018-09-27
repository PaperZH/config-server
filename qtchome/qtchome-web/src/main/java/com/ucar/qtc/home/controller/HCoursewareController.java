package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.service.HCoursewareService;
import com.ucar.qtcassist.api.model.BaseCoursewareListDTO;
import com.ucar.qtcassist.api.model.CoursewareTypeDTO;
import com.ucar.qtcassist.api.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月30日
 */
@RestController
public class HCoursewareController {

    @Autowired
    HCoursewareService hcoursewareService;

    @RequestMapping(value = "/frontPage/courseware/getAllBaseCoursewares")
    Result<List<BaseCoursewareListDTO>> getAllBaseCoursewares(){
        return hcoursewareService.getAllBaseCoursewares();
    }

    @RequestMapping(value = "/frontPage/courseware/getAllTypes")
    Result<List<CoursewareTypeDTO>> getAllTypes(){
        return hcoursewareService.getAllType();
    }

    @RequestMapping(value = "/courseware/upLoad",method = RequestMethod.POST)
    Result upLoad( MultipartFile file) throws Exception{
        return hcoursewareService.uploadCourseware(file);
    }
}
