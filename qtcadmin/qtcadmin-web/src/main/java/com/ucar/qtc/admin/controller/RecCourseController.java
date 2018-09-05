package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.CourseDO;
import com.ucar.qtc.admin.domain.RecommandCourseDO;
import com.ucar.qtc.admin.service.RecommandCourseService;
import com.ucar.qtc.admin.service.rpc.ReccourceServiceRpc;
import com.ucar.qtc.admin.vo.QueryVO;
import com.ucar.qtc.common.dto.QueryDO;
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
    private ReccourceServiceRpc recCourceService;

    @Autowired
    private RecommandCourseService recommandCourseService;

    @PostMapping("getCourse")
    public ResponseResult getCourseList(@RequestBody QueryDO queryDO){
        return ResponseResult.ok().put("list",recCourceService.getCourseList(queryDO));
    }

    @PostMapping
    public ResponseResult getRecourseList(@RequestBody Map<String,Object> map){

        List<Long> listIds = recommandCourseService.list(map);
        if(listIds == null){
            return ResponseResult.ok("无数据");
        }
        Map<String,Object> ids = new HashMap<String,Object>();
        ids.put("id",listIds);
        List<CourseDO> listCourseDo =  recCourceService.getCourseListByIds(ids);
        List<CourseDO> list = new ArrayList<CourseDO>();
        Iterator iterator = listIds.iterator();
        Iterator iterator1;
        while(iterator.hasNext()){
            Long temp = (Long) iterator.next();
             iterator1 = listCourseDo.iterator();
            while(iterator1.hasNext()){
                CourseDO tempCouse = (CourseDO)iterator1.next();
                if(tempCouse.getId()==temp)
                {
                    list.add(tempCouse);
                }
            }
        }
        return ResponseResult.ok().put("list",list);
    }

    @PutMapping
    public ResponseResult saveRecourse(@RequestBody RecommandCourseDO courseDO){

        if(recommandCourseService.get(courseDO.getId())!=null){
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

    @DeleteMapping
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

    public ResponseResult removeReccourse(@RequestBody CourseDO courseDO){

        if(recommandCourseService.remove(courseDO.getId())>0)
            return ResponseResult.ok();
        else
            return ResponseResult.error();
    }
}
