package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.service.HCoursewareService;
import com.ucar.qtcassist.api.model.CourseCoursewareDTO;
import com.ucar.qtc.home.service.CourseCoursewareService;
import com.ucar.qtcassist.api.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年09月05日
 */
@RequestMapping("/courseCourseware")
@RestController
public class CourseCoursewareController {
    @Autowired
    CourseCoursewareService courseCoursewareService;

    @Autowired
    HCoursewareService hCoursewareService;

    private static Logger LOGGER = LoggerFactory.getLogger(CourseCoursewareController.class);


    @RequestMapping(value = "/addCourseCourseware",method = RequestMethod.POST)
    public Result add(@RequestBody CourseCoursewareDTO courseCoursewareDTO){
        if(!courseCoursewareDTO.getFileUrl().equals("")&&courseCoursewareDTO.getFileUrl()!=null){
            Result rid=hCoursewareService.uploadCourseware(courseCoursewareDTO);
            int id = (int)rid.getRe();
            Long Lid= Long.valueOf(id);
            return courseCoursewareService.addCourseCourseware(courseCoursewareDTO.getCourseId(),Lid);

        }else if(courseCoursewareDTO.getBaseCoursewareId()!=null){
            Result rid =hCoursewareService.addCourseware(courseCoursewareDTO.getBaseCoursewareId(),courseCoursewareDTO.getHour());
            int id = (int)rid.getRe();
            Long Lid= Long.valueOf(id);
            return courseCoursewareService.addCourseCourseware(courseCoursewareDTO.getCourseId(),Lid);
        }
        return null;
    }


    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public Result test(){
        return courseCoursewareService.addCourseCourseware(1l,2l);
    }

}
