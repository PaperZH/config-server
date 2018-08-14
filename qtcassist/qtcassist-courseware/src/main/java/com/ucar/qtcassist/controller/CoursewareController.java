package com.ucar.qtcassist.controller;

import com.ucar.qtcassist.model.DTO.BackCoursewareDTO;
import com.ucar.qtcassist.model.DTO.BaseCoursewareListDTO;
import com.ucar.qtcassist.model.DTO.FrontCoursewareDTO;
import com.ucar.qtcassist.service.CoursewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月13日
 */
@RestController
@RequestMapping("/course")
public class CoursewareController {
    @Autowired
    CoursewareService coursewareService;

    /**
     * 系统库课件列表
     * */
    @RequestMapping(value = "/getAllBaseCoursewares" ,method = RequestMethod.POST)
    List<BaseCoursewareListDTO> getAllBaseCoursewares(){
        return coursewareService.getAllBaseCoursewares();
    }

    /**
     * 从系统库中选择课件进行添加
     * */
    @RequestMapping(value = "/addCoursewareFromLocal",method = RequestMethod.POST)
    BackCoursewareDTO addCoursewareFromLocal(@RequestParam Long coursewareId,@RequestParam Long courseId ){


        return null;
    }


    /**
     * 从本地上传课件进行添加
     * */
    @RequestMapping(value="/addCoursewareFromSys" , method = RequestMethod.POST)
    BackCoursewareDTO addCoursewareFromSys (@RequestParam("file") MultipartFile file, HttpServletRequest request){



        return null;
    }
}
