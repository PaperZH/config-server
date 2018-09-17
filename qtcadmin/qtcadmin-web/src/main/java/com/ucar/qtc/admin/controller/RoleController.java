package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.RoleDO;
import com.ucar.qtc.admin.service.RoleService;
import com.ucar.qtc.common.annotation.Log;
import com.ucar.qtc.common.utils.PageUtils;
import com.ucar.qtc.common.utils.Query;
import com.ucar.qtc.common.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 角色管理
 * @date: 2018/8/7 10:19
 */
@Api(description = "角色服务API接口")
@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    /**
     * 角色列表
     * @param params
     * @return
     */
    @ApiOperation(value="获取角色列表信息", notes="获取角色列表信息")
    @Log("获取角色列表")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<RoleDO> roleDOS = roleService.list(query);
        int total = roleService.count(query);
        PageUtils pageUtil = new PageUtils(roleDOS, total);
        return pageUtil;
    }

    /**
     * 根据用户查询角色
     * @param userId
     * @return
     */
    @ApiIgnore
    @GetMapping(value="/userId/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Long> roleIdByUserId(@PathVariable Long userId){
        return roleService.RoleIdsByUserId(userId);
    }

    /**
     * 保存角色
     * @param roleDO
     * @return
     */
    @ApiOperation(value="保存角色信息", notes="保存角色信息")
    @Log("保存角色")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult save(@RequestBody RoleDO roleDO){
        if(roleService.save(roleDO)>0){
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 更新角色
     * @param roleDO
     * @return
     */
    @ApiOperation(value="修改角色信息", notes="修改角色信息")
    @Log("修改角色")
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult update(@RequestBody RoleDO roleDO){
        if(roleService.update(roleDO)>0){
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @ApiOperation(value="删除角色信息", notes="删除角色信息")
    @Log("删除角色")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult remove(Long id) {
        return ResponseResult.operate (roleService.remove(id) > 0);
    }
}
