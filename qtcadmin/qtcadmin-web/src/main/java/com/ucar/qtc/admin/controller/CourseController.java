package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.rpc.CourseServiceRpc;
import com.ucar.qtc.admin.vo.CourseVO;
import com.ucar.qtc.admin.vo.QueryVO;
import com.ucar.qtc.common.utils.Page;
import com.ucar.qtc.common.utils.ResponseResult;
import com.ucar.qtc.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: train
 * @description: 后台管理课程信息
 * @author: guodong.zhang01
 * @create: 2018-09-17 14:05
 **/
@RequestMapping("/course")
@RestController
public class CourseController {
    @Autowired
    private CourseServiceRpc courseServiceRpc;

    @PostMapping(value = "getCourseList",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<Page<CourseVO>> getCourseList(@RequestBody QueryVO queryVO ){
        return courseServiceRpc.getCourseList(queryVO);
    }
}
