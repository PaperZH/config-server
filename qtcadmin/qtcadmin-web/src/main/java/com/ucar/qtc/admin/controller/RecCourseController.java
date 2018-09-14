package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.RecommandCourseDO;
import com.ucar.qtc.admin.service.RecCourseService;
import com.ucar.qtc.admin.rpc.CourseServiceRpc;
import com.ucar.qtc.admin.vo.CourseVO;
import com.ucar.qtc.admin.vo.QueryVO;
import com.ucar.qtc.common.annotation.Log;
import com.ucar.qtc.common.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * build:guodong.zhang
 *
 * 功能描述:推荐课程
 *
 */
@Api(description = "首页课程推荐服务API接口")
@RestController
@RequestMapping("/recCourse")
public class RecCourseController {

    @Autowired
    private CourseServiceRpc courseService;

    @Autowired
    private RecCourseService recCourseService;

    @ApiOperation(value="获取推荐课程信息", notes="获取推荐课程信息")
    @PostMapping(value="getCourse", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult getCourseList(@RequestBody QueryVO queryVO){
        return ResponseResult.ok().put("listCourse",courseService.getCourseIdAndCourseName(queryVO));
    }

    @ApiOperation(value="获取推荐课程列表信息", notes="获取推荐课程列表信息")
    @Log("获取推荐课程列表信息")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult getRecourseList(@RequestBody QueryVO queryVO){
       List<CourseVO> result = recCourseService.listRecCourseByQuery(queryVO);
       if(result == null)
           return ResponseResult.error();

        return ResponseResult.ok().put("list",result);
    }

    @ApiOperation(value="保存推荐课程信息", notes="保存推荐课程信息")
    @Log("保存推荐课程信息")
    @PostMapping(value="save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult saveRecourse(@RequestBody RecommandCourseDO courseDO){
        if(courseDO.getCourseId() == null)
            return ResponseResult.error();

        if(recCourseService.get(courseDO.getCourseId())!=null){
            courseDO.setDelFlag(1);
            if(recCourseService.update(courseDO)>0)
                return ResponseResult.ok();
            else
                return ResponseResult.error();
        }else if(recCourseService.save(courseDO)>0){
            return ResponseResult.ok();
        } else {
            return ResponseResult.error();
        }
    }

    @ApiIgnore
    @Log("批量删除推荐课程信息")
    @DeleteMapping(value="/batchRemove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult batchRemoveRecCourseByIds(@RequestBody Map<String,Object> params){

        Object obj = params.get("id");
        List<Integer> list= (List<Integer>) obj;
        Long[] idForLong = new Long[list.size()];
        for(int i = 0 ;i < list.size();i++ ){
            idForLong[i] = Long.valueOf(list.get(i));
        }
        if(recCourseService.batchremove(idForLong)>0)
            return ResponseResult.ok();
        else
            return ResponseResult.error();
    }

    @ApiOperation(value="删除推荐课程信息", notes="删除推荐课程信息")
    @Log("删除推荐课程信息")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult removeReccourse(@RequestParam(value="id",defaultValue="-1") Long  courseId){

        if(recCourseService.remove(courseId)>0)
            return ResponseResult.ok();
        else
            return ResponseResult.error();
    }
}
