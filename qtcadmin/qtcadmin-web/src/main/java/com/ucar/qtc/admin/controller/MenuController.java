package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.MenuDO;
import com.ucar.qtc.admin.domain.Tree;
import com.ucar.qtc.admin.service.MenuService;
import com.ucar.qtc.common.annotation.Log;
import com.ucar.qtc.common.context.FilterContextHandler;
import com.ucar.qtc.common.dto.MenuDTO;
import com.ucar.qtc.common.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 菜单管理
 * @date: 2018/8/7 10:19
 */
@Api(description = "菜单服务API接口")
@RequestMapping("/menu")
@RestController()
public class MenuController {
    @Autowired
    MenuService menuService;

    @ApiIgnore
    @Log("访问菜单")
    @GetMapping(value="tree", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Tree<MenuDO>  tree(){
        return menuService.getTree();
    }

    @ApiOperation(value="获取菜单信息", notes="获取菜单信息")
    @Log("访问菜单列表")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Tree<MenuDO>>  list(){
        return menuService.getTree().getChildren();
    }

    /**
     * 根据ID获取菜单
     * @param id
     * @return
     */
    @ApiOperation(value="获取ID获取菜单信息", notes="获取ID获取菜单信息")
    @GetMapping(value="{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MenuDO get(@PathVariable("id") Long id) {
        MenuDO menu = menuService.get(id);
        return menu;
    }

    /**
     * 菜单列表
     * @param params
     * @return
     */
    @ApiOperation(value="获取菜单列表信息", notes="获取菜单列表信息")
    @Log("访问菜单列表")
    @GetMapping(value="list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MenuDO> list(@RequestParam Map<String, Object> params) {
        List<MenuDO> menus = menuService.list(params);
        return menus;
    }

    /**
     * 修改菜单
     * @param menuDO
     * @return
     */
    @ApiOperation(value="修改菜单信息", notes="修改菜单信息")
    @Log("修改菜单")
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult update(@RequestBody MenuDO menuDO){
        if(menuService.update(menuDO)>0){
            return ResponseResult.ok();
        }
        return  ResponseResult.error();
    }

    /**
     * 添加菜单
     * @param menuDO
     * @return
     */
    @ApiOperation(value="添加菜单信息", notes="添加菜单信息")
    @Log("添加菜单")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult save(@RequestBody MenuDO menuDO){
        menuDO.setDelFlag(1);
        return ResponseResult.operate(menuService.save(menuDO)>0);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @ApiOperation(value="删除菜单信息", notes="删除菜单信息")
    @Log("删除菜单")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult remove(Long id){
        if(menuService.remove(id)>0){
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 用户菜单
     * @return
     */
    @ApiOperation(value="获取当前用户菜单信息", notes="获取当前用户菜单信息")
    @GetMapping(value="userMenus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MenuDTO> userMenus(){
        List<MenuDO> menuDOS = menuService.userMenus(Long.parseLong(FilterContextHandler.getUserID()));
        List<MenuDTO> menuDTOS = new ArrayList<>();
        for (MenuDO menuDO:menuDOS){
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setMenuId(menuDO.getMenuId());
            menuDTO.setUrl(menuDO.getUrl());
            menuDTO.setPerms(menuDO.getPerms());
            menuDTOS.add(menuDTO);
        }
        return menuDTOS;
    }

    @ApiIgnore
    @GetMapping(value="clearCache", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult clearCache(){
        Boolean flag = menuService.clearCache(Long.parseLong(FilterContextHandler.getUserID()));
        if (flag){
            return  ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 当前用户菜单的树形结构
     * @return
     */
    @ApiOperation(value="获取当前用户菜单信息", notes="获取当前用户菜单信息")
    @Log("当前用户菜单")
    @RequestMapping(value="/currentUserMenus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Tree<MenuDO>> currentUserMenus() {
        List<Tree<MenuDO>> menus = menuService.listMenuTree(Long.parseLong(FilterContextHandler.getUserID()));
        return menus;
    }

    @ApiOperation(value="获取角色菜单信息", notes="获取角色菜单信息")
    @GetMapping(value="/roleId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Long> menuIdsByRoleId(Long roleId){
        return menuService.MenuIdsByRoleId(roleId);
    }
}
