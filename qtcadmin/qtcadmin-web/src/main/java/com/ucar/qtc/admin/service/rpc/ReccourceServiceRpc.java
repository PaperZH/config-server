package com.ucar.qtc.admin.service.rpc;

import com.ucar.qtc.admin.domain.CourseDO;
import com.ucar.qtc.admin.vo.CourseDetailVO;
import com.ucar.qtc.admin.vo.CourseVO;
import com.ucar.qtc.admin.vo.QueryVO;
import com.ucar.qtc.common.dto.QueryDO;
import com.ucar.qtc.common.utils.Page;
import com.ucar.qtc.common.utils.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：远程服务调用assist课程模块接口
 */
@FeignClient("qtcassist")
@Service
public interface ReccourceServiceRpc {

    /**
     * build:guodong.zhang
     *
     * 功能描述：查看条件课程列表信息
     * @param queryDO
     * @return
     */
    @PostMapping("/course/getRecCourseList")
    List<CourseDO> getCourseList(@RequestBody QueryDO queryDO);

    /**
     * build:guodong.zhang
     *
     * 功能描述：通过ID批量查询课程列表信息
     * @param params
     * @return
     */
    @PostMapping("/course/getCourseListByIds")
    List<CourseDO> getCourseListByIds(@RequestBody Map<String,Object> params);

    /**
     * build:guodong.zhang
     *
     * 功能描述：通过课程ID查看课程详细信息
     * @param courseId
     * @return
     */
    @GetMapping("/course/getRecCourseDetail/{courseId}")
    CourseDO getCourseDetail(@PathVariable("courseId") Long courseId);


}
