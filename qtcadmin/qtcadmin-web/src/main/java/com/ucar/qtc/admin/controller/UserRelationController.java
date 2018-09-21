package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.UserRelationDO;
import com.ucar.qtc.admin.service.UserRelationService;
import com.ucar.qtc.common.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

/**
 * @program: train
 * @description: 用户关系关联API接口
 * @author: guodong.zhang01
 * @create: 2018-09-20 17:32
 **/
@Api(description = "用户关系API接口")
@RequestMapping("/userRelation")
@RestController
public class UserRelationController {

    @Autowired
    private UserRelationService userRelationService;

    @ApiOperation(value = "添加用户关联关系", notes = "注意是按照关系类别添加用户关系")
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult addRelation(@RequestBody UserRelationDO userRelationDO){
        if(userRelationService.saveUserRelation(userRelationDO)>1){
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    @ApiOperation(value = "删除用户关联关系", notes = "注意是按照关系id")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult removeRelation(@RequestParam Long id){
        if(userRelationService.removeUserRelation(id)>1){
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    @ApiOperation(value = "更新用户关联关系", notes = "按照id值更改")
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult updateRelation(@RequestBody UserRelationDO userRelationDO){
        if(userRelationService.updateUserRelation(userRelationDO)>1){
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }
}
