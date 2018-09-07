package com.ucar.qtc.admin.rpc;

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
public interface CourseServiceRpc {

    /**
     * build:guodong.zhang
     *
     * 功能描述：查看条件课程列表信息
     * @param queryVO
     * @return
     */
    @PostMapping("course/getCourseIdAndCourseName")
    Map<String, Object> getCourseIdAndCourseName(@RequestBody QueryVO queryVO);

    @PostMapping("course/getRecCourseList")
    Result<List<CourseVO>> getRecCourseList(@RequestBody QueryVO queryVO);
}
