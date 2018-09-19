package com.ucar.qtc.admin.rpc;

import com.ucar.qtc.admin.dto.BackCoursewareDTO;
import com.ucar.qtc.admin.dto.BackDTO;
import com.ucar.qtc.admin.vo.CoursewareQueryVO;
import com.ucar.qtc.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: train
 * @description: 远程调用assist里的课件接口
 * @author: guodong.zhang01
 * @create: 2018-09-17 14:16
 **/
@FeignClient("qtcassist")
@Service
public interface CoursewareServiceRpc {

    @RequestMapping("courseware/queryList")
    Result<BackDTO> queryList(@RequestBody CoursewareQueryVO coursewareQueryVO);

}
