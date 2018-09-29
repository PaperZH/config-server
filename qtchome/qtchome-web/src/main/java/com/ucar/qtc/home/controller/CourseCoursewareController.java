package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.service.CourseCoursewareService;
import com.ucar.qtc.home.service.HCoursewareService;
import com.ucar.qtcassist.api.model.AddCoursewarePageListDTO;
import com.ucar.qtcassist.api.model.CourseCoursewareDTO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.VO.AddCoursewareQueryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年09月05日
 */
@RestController
@RequestMapping("/courseCourseware")
public class CourseCoursewareController {
    @Autowired
    CourseCoursewareService courseCoursewareService;

    @Autowired
    HCoursewareService hCoursewareService;

    private static Logger LOGGER = LoggerFactory.getLogger(CourseCoursewareController.class);


    @RequestMapping(value = "/addCourseCourseware", method = RequestMethod.POST)
    public Result add(@RequestBody CourseCoursewareDTO courseCoursewareDTO) {
        System.out.println(courseCoursewareDTO.getFlag());
        if(courseCoursewareDTO.getFlag() == 1) {
            Result rid = hCoursewareService.saveCourseware(courseCoursewareDTO);
            int id = (int) rid.getRe();
            Long Lid = Long.valueOf(id);
            return courseCoursewareService.addCourseCourseware(courseCoursewareDTO.getCourseId(), Lid);

        } else if(courseCoursewareDTO.getBaseCoursewareId() != null) {
            Result rid = hCoursewareService.addCourseware(courseCoursewareDTO);
            int id = (int) rid.getRe();
            Long Lid = Long.valueOf(id);
            return courseCoursewareService.addCourseCourseware(courseCoursewareDTO.getCourseId(), Lid);
        }
        return null;
    }

    @RequestMapping(value = "/getAddCoursewarePageList",method = RequestMethod.POST)
    Result<AddCoursewarePageListDTO> getAddCoursewarePageList(@RequestBody AddCoursewareQueryVO addCoursewareQueryVO){
        return courseCoursewareService.getAddCoursewarePageList(addCoursewareQueryVO);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Result test() {
        return courseCoursewareService.addCourseCourseware(1L, 2L);
    }

}
