package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.BannerDO;
import com.ucar.qtc.admin.domain.CourseDO;
import com.ucar.qtc.admin.domain.RecommandCourseDO;
import com.ucar.qtc.admin.rpc.CourseServiceRpc;
import com.ucar.qtc.admin.service.BannerService;
import com.ucar.qtc.admin.service.RecommandCourseService;
import com.ucar.qtc.admin.vo.CourseVO;
import com.ucar.qtc.admin.vo.QueryVO;
import com.ucar.qtc.common.utils.PageUtils;
import com.ucar.qtc.common.utils.Query;
import com.ucar.qtc.common.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pages")
public class FrontPageController {


    @Autowired
    private BannerService bannerService;

    @Autowired
    private RecommandCourseService recommandCourseService;

    private CourseServiceRpc courseService;

    @GetMapping("getRecBanner")
    public ResponseResult bannerList() {
        int limit = 6;
        int offset = 0;
        int page = 1;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("limit",limit);
        params.put("offset",offset);
        params.put("page",page);
        Query query = new Query(params);
        List<BannerDO> bannerList = bannerService.list(query);
        int total = bannerService.count(query);
        PageUtils pageUtils = new PageUtils(bannerList, total);
        return ResponseResult.page(pageUtils);
    }

    @GetMapping("getRecCourse")
    public ResponseResult getRecourseList(){

        List<RecommandCourseDO> recommandCourseList = recommandCourseService.list(null);
        if(recommandCourseList.size() == 0){
            return ResponseResult.error(-1,"无推荐课程信息");
        }
        Long[] listIds = new Long[recommandCourseList.size()];
        Iterator iterator = recommandCourseList.iterator();
        int index = 0;
        while(iterator.hasNext()){
            listIds[index++] = ((RecommandCourseDO)iterator.next()).getId();
        }
        QueryVO queryVO = new QueryVO();
        queryVO.setCourseIds(listIds);
        Map<String,Object> ids = new HashMap<String,Object>();
        ids.put("id",listIds);
        List<CourseVO> listCourseVo = courseService.getRecCourseList(queryVO).getRe();
        List<CourseVO> list = new ArrayList<CourseVO>();
        for(int i = 0;i <listIds.length; i++){
            iterator = listCourseVo.iterator();
            while(iterator.hasNext()){
                CourseVO tempCourseVO = (CourseVO)iterator.next();
                if(tempCourseVO.getCourseId() == listIds[i])
                    list.add(tempCourseVO);
            }
        }
        return ResponseResult.ok().put("list",list);
    }

}
