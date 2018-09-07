package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.RecommandCourseDO;
import com.ucar.qtc.admin.service.RecCourseService;
import com.ucar.qtc.admin.rpc.CourseServiceRpc;
import com.ucar.qtc.admin.vo.CourseVO;
import com.ucar.qtc.admin.vo.QueryVO;
import com.ucar.qtc.common.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * build:guodong.zhang
 *
 * 功能描述:推荐课程
 *
 */
@RestController
@RequestMapping("/recCourse")
public class RecCourseController {

    @Autowired
    private CourseServiceRpc courseService;

    @Autowired
    private RecCourseService recCourseService;

    @PostMapping("getCourse")
    public ResponseResult getCourseList(@RequestBody QueryVO queryVO){
        return ResponseResult.ok().put("listCourse",courseService.getCourseIdAndCourseName(queryVO));
    }

    @PostMapping
    public ResponseResult getRecourseList(@RequestBody QueryVO queryVO){
       List<CourseVO> result = recCourseService.listRecCourseByQuery(queryVO);
       if(result == null)
           return ResponseResult.error();

        return ResponseResult.ok().put("list",result);
    }

    @PostMapping("save")
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

    @DeleteMapping("/batchRemove")
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
    @DeleteMapping
    public ResponseResult removeReccourse(@RequestParam(value="id",defaultValue="-1") Long  courseId){

        if(recCourseService.remove(courseId)>0)
            return ResponseResult.ok();
        else
            return ResponseResult.error();
    }
}
