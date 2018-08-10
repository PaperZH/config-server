package com.ucar.qtc.base.controller;


import com.ucar.qtc.base.service.LogService;
import com.ucar.qtc.common.dto.LogDO;
import com.ucar.qtc.common.utils.PageUtils;
import com.ucar.qtc.common.utils.Query;
import com.ucar.qtc.common.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 基础日志服务控制层
 * @date: 2018/8/7 10:19
 */
@RequestMapping("/log")
@RestController
public class LogController {
    @Autowired
    LogService logService;

    @GetMapping()
    ResponseResult list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return ResponseResult.page(new PageUtils(logService.queryList(query), logService.count(query)));
    }

    @PostMapping("/save")
    ResponseResult save(@RequestBody LogDO logDO) {
        if (logService.save(logDO) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    @DeleteMapping()
    ResponseResult remove(Long id) {
        if (logService.remove(id) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    @PostMapping("/batchRemove")
    ResponseResult batchRemove(@RequestParam("ids[]") Long[] ids) {
        int r = logService.batchRemove(ids);
        if (r > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }
}
