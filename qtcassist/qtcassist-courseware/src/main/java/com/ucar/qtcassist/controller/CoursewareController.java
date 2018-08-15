package com.ucar.qtcassist.controller;

import com.ucar.qtcassist.model.DTO.BackCoursewareDTO;
import com.ucar.qtcassist.model.DTO.BaseCoursewareListDTO;
import com.ucar.qtcassist.service.BaseCoursewareService;
import com.ucar.qtcassist.service.RemoteFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    BaseCoursewareService baseCoursewareService;

    @Autowired
    RemoteFileService remoteFileService;

    private static Logger LOGGER = LoggerFactory.getLogger(CoursewareController.class);
    /**
     * 系统库课件列表
     */
    @RequestMapping(value = "/getAllBaseCoursewares", method = RequestMethod.POST)
    public List<BaseCoursewareListDTO> getAllBaseCoursewares() {
        return baseCoursewareService.getAllBaseCoursewares();
    }

    /**
     * 从本地上传课件进行添加
     */
    @RequestMapping(value = "/addCoursewareFromLocal", method = RequestMethod.POST)
    public BackCoursewareDTO addCoursewareFromSys(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        return null;
    }
}
