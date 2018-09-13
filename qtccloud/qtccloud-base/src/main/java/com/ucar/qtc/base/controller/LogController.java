package com.ucar.qtc.base.controller;

import com.ucar.qtc.base.service.LogService;
import com.ucar.qtc.base.dto.LogDO;
import com.ucar.qtc.base.utils.PageUtils;
import com.ucar.qtc.base.utils.Query;
import com.ucar.qtc.base.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 基础日志服务控制层
 * @date: 2018/8/7 10:19
 */
@Api(description = "日志基础服务API接口")
@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    LogService logService;

    /**
     * 日志列表
     * @param params
     * @return
     */
    @ApiOperation(value="获取日志列表信息", notes="获取日志列表信息")
    @GetMapping()
    ResponseResult list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return ResponseResult.page(new PageUtils(logService.queryList(query), logService.count(query)));
    }

    /**
     * 保存日志信息
     * @param logDO
     * @return
     */
    @ApiOperation(value="保存日志信息", notes="保存日志信息")
    @PostMapping("/save")
    ResponseResult save(@RequestBody LogDO logDO) {
        if (logService.save(logDO) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 删除日志
     * @param id
     * @return
     */
    @ApiOperation(value="删除日志信息", notes="删除日志信息")
    @DeleteMapping()
    ResponseResult remove(Long id) {
        if (logService.remove(id) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 批量删除
     * @param params
     * @return
     */
    @ApiOperation(value="批量删除日志信息", notes="批量删除日志信息")
    @PostMapping("/batchRemove")
    ResponseResult batchRemove(@RequestBody Map<String, Object> params) {
        Object obj = params.get("ids");
        if (obj == null) {
            return ResponseResult.error();
        }
        List<Integer> list = (List<Integer>) obj;
        Long[] ids = new Long[list.size()];
        for (int index=0;index<list.size();index++) {
            ids[index] = Long.valueOf(list.get(index));
        }
        int r = logService.batchRemove(ids);
        if (r > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }
}
