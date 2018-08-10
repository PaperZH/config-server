package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.RoleDO;
import com.ucar.qtc.admin.service.RoleService;
import com.ucar.qtc.common.utils.PageUtils;
import com.ucar.qtc.common.utils.Query;
import com.ucar.qtc.common.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 角色管理
 * @date: 2018/8/7 10:19
 */
@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping()
    PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<RoleDO> roleDOS = roleService.list(query);
        int total = roleService.count(query);
        PageUtils pageUtil = new PageUtils(roleDOS, total);
        return pageUtil;
    }

    @GetMapping("/userId/{userId}")
    List<Long> roleIdByUserId(@PathVariable Long userId){
        return roleService.RoleIdsByUserId(userId);
    }

    @PostMapping
    ResponseResult save(@RequestBody RoleDO roleDO){
        if(roleService.save(roleDO)>0){
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    @PutMapping
    ResponseResult update(@RequestBody RoleDO roleDO){
        if(roleService.update(roleDO)>0){
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

}
