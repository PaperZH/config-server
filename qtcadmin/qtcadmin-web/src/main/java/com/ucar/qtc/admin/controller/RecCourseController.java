package com.ucar.qtc.admin.controller;

import com.alibaba.fastjson.JSON;
import com.ucar.qtc.admin.domain.CourseDO;
import com.ucar.qtc.admin.domain.RecommandCourseDO;
import com.ucar.qtc.admin.service.RecommandCourseService;
import com.ucar.qtc.admin.rpc.CourseServiceRpc;
import com.ucar.qtc.admin.vo.CourseVO;
import com.ucar.qtc.admin.vo.QueryVO;
import com.ucar.qtc.common.dto.QueryDO;
import com.ucar.qtc.common.utils.Query;
import com.ucar.qtc.common.utils.ResponseResult;
import com.ucar.qtc.common.utils.Result;
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
    private RecommandCourseService recommandCourseService;

    @PostMapping("getCourse")
    public ResponseResult getCourseList(@RequestBody QueryVO queryVO){
        return ResponseResult.ok().put("listCourse",courseService.getCourseIdAndCourseName(queryVO));
    }

    @PostMapping
    public ResponseResult getRecourseList(@RequestBody QueryVO queryVO){
        List<Long> listCourseIds = new ArrayList<Long>();

        //首先通过名字查询课程信息
        List courseVOS = (List) courseService.getCourseIdAndCourseName(queryVO).get("ids");
        if(courseVOS==null||courseVOS.isEmpty()){
            return ResponseResult.error(-1,"无课程信息");
        }

        Map<String,Object> resMap = new HashMap<String,Object>();
        Iterator iterator = courseVOS.iterator();
        //取出课程信息ID
        while(iterator.hasNext()){
            Map tempMap = (Map)iterator.next();
            listCourseIds.add(((Integer) tempMap.get("id")).longValue());
        }
        resMap.put("ids",listCourseIds);
        List<RecommandCourseDO> recommandCourseList = recommandCourseService.list(resMap);
        if(recommandCourseList.size() == 0){
            return ResponseResult.error(-1,"无推荐课程信息");
        }
        Long[] ids = new Long[recommandCourseList.size()];
        iterator = recommandCourseList.iterator();
        int index = 0;
        while(iterator.hasNext()){
            ids[index++] = ((RecommandCourseDO)iterator.next()).getCourseId();
        }
        List<CourseVO> recCourseVOS = new ArrayList<CourseVO>();
        queryVO.setCourseIds(ids);
        recCourseVOS = courseService.getRecCourseList(queryVO).getRe();
        List<CourseVO> result = new ArrayList<CourseVO>();

        //按照ord_num的顺序，将数据填充
        for(int i = 0;i < ids.length;i ++){
            iterator = recCourseVOS.iterator();
            while(iterator.hasNext()){
                CourseVO temp = (CourseVO) iterator.next();
                if(temp.getCourseId() == ids[i]){
                    temp.setOrderNum(recommandCourseList.get(i).getOrderNum());
                    result.add(temp);
                }
            }

        }
        return ResponseResult.ok().put("list",result);
    }

    @PostMapping("save")
    public ResponseResult saveRecourse(@RequestBody RecommandCourseDO courseDO){
        if(courseDO.getCourseId() == null)
            return ResponseResult.error();

        if(recommandCourseService.get(courseDO.getCourseId())!=null){
            courseDO.setDelFlag(1);
            if(recommandCourseService.update(courseDO)>0)
                return ResponseResult.ok();
            else
                return ResponseResult.error();
        }else if(recommandCourseService.save(courseDO)>0){
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
        if(recommandCourseService.batchremove(idForLong)>0)
            return ResponseResult.ok();
        else
            return ResponseResult.error();
    }
    @DeleteMapping
    public ResponseResult removeReccourse(@RequestParam(value="id",defaultValue="-1") Long  courseId){

        if(recommandCourseService.remove(courseId)>0)
            return ResponseResult.ok();
        else
            return ResponseResult.error();
    }
}
