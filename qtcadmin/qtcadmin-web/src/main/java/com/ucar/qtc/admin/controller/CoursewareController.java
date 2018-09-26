package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.dto.BackDTO;
import com.ucar.qtc.admin.rpc.CoursewareServiceRpc;
import com.ucar.qtc.admin.vo.CoursewareQueryVO;
import com.ucar.qtc.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: train
 * @description: 后改对课件的管理
 * @author: guodong.zhang01
 * @create: 2018-09-17 14:05
 **/
@RequestMapping("/courseware")
@RestController
public class CoursewareController {

    @Autowired
    private CoursewareServiceRpc coursewareServiceRpc;

    @RequestMapping(value = "getCoursewareList",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<BackDTO> getCoursewareList(@RequestBody CoursewareQueryVO coursewareQueryVO){
        return coursewareServiceRpc.queryList(coursewareQueryVO);
    }
}
