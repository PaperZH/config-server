package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.UserDO;
import com.ucar.qtc.admin.dto.UserDTO;
import com.ucar.qtc.admin.dto.do2dto.UserConvert;
import com.ucar.qtc.admin.service.RoleService;
import com.ucar.qtc.admin.service.UserService;
import com.ucar.qtc.admin.utils.MD5Utils;
import com.ucar.qtc.common.annotation.Log;
import com.ucar.qtc.common.dto.LoginUserDTO;
import com.ucar.qtc.common.utils.PageUtils;
import com.ucar.qtc.common.utils.Query;
import com.ucar.qtc.common.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 用户管理
 * @date: 2018/8/7 10:19
 */
@Api(description = "用户服务API接口")
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

	/**
	 * 根据用户id获取用户
	 * @param id
	 * @return
	 */
	@ApiOperation(value="根据ID获取用户信息", notes="根据ID获取用户信息")
    @GetMapping(value="{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult get(@PathVariable("id") Long id ){
		UserDTO userDTO = UserConvert.MAPPER.do2dto(userService.get(id));
    	return ResponseResult.ok().put("data",userDTO);
	}

	/**
	 * 登录的当前用户，前台需要验证用户登录的页面可以调用此方法
	 * @return
	 */
	@ApiOperation(value="当前登陆用户", notes="当前登陆用户")
	@GetMapping(value="/currentUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	LoginUserDTO currentUser(){
		LoginUserDTO user = userService.currentUser();
		return user;
	}

	/**
	 * 登录的当前用户，前台需要验证用户登录的页面可以调用此方法
	 * @return
	 */
	@ApiOperation(value="前台当前登陆用户", notes="前台当前登陆用户")
	@GetMapping(value="/front/currentUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseResult frontCurrentUser(){
		UserDO userDO = userService.frontCurrentUser();
		if (userDO == null) {
			return ResponseResult.error("获取当前用户信息为空");
		}
		return ResponseResult.ok("获取用户信息成功").put("user",userDO);
	}

	/**
	 * 根据用户信息获得老师信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value="根据ID获取用户老师信息", notes="根据ID获取用户老师信息")
	@GetMapping(value="/getTeacherRelationInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseResult getTeacherRelationInfo(@RequestParam(value = "userId",required = true) Long id,@RequestParam(value = "relationType",required = true) int type){

		List<UserDTO> teacher = new ArrayList<UserDTO>();
		Iterator iterator = userService.getTeacherById(id,type).iterator();
		while(iterator.hasNext()){
			teacher.add(UserConvert.MAPPER.do2dto((UserDO)iterator.next()));
		}
		return ResponseResult.ok().put("teacher",teacher);
	}

	/**
	 * 根据用户信息获得学员信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value="根据ID获取用户学员信息", notes="根据ID获取用户学员信息")
	@GetMapping(value="/getStudentRelationInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseResult getStudentRelationInfo(@RequestParam(value = "userId",required = true) Long id,@RequestParam(value = "relationType",required = true) int type){

		List<UserDTO> student = new ArrayList<UserDTO>();
		Iterator iterator = userService.getStudentById(id,type).iterator();
		while(iterator.hasNext()){
			student.add(UserConvert.MAPPER.do2dto((UserDO)iterator.next()));
		}
		return ResponseResult.ok().put("student",student);
	}

	/**
	 * 分页查询用户
	 * @param params
	 * @return
	 */
	@ApiOperation(value="获取用户信息", notes="获取用户信息")
	@Log("获取用户列表")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult listByPage(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<UserDO> list = userService.list(query);
        List<UserDTO> userDTOS = UserConvert.MAPPER.dos2dtos(list);
        int total = userService.count(query);
        PageUtils pageUtil = new PageUtils(userDTOS, total);
        return ResponseResult.ok().put("page",pageUtil);
    }

	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	@ApiOperation(value="添加用户信息", notes="添加用户信息")
	@Log("添加用户")
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult save(@RequestBody UserDO user) {
		user.setDelFlag(1);
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		return ResponseResult.operate(userService.save(user) > 0);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@ApiOperation(value="修改用户信息", notes="修改用户信息")
	@Log("修改用户")
	@PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult update(@RequestBody UserDO user) {
		return ResponseResult.operate(userService.update(user) > 0);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@ApiOperation(value="修改用户昵称信息", notes="修改用户昵称信息")
	@Log("修改用户昵称和图片")
	@PostMapping(value="/changeProfile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseResult changeProfile(@RequestBody UserDO user) {
		ResponseResult result = ResponseResult.operate(userService.changeProfile(user) > 0);
		LoginUserDTO dto = userService.currentUser();
		return result.data(dto);
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@ApiOperation(value="删除用户信息", notes="删除用户信息")
	@Log("删除用户")
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult remove(Long id) {
		return ResponseResult.operate (userService.remove(id) > 0);
	}

	@ApiIgnore
	@PostMapping(value="/batchRemove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
    ResponseResult batchRemove(@RequestParam("ids[]") Long[] userIds) {
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return ResponseResult.ok();
		}
		return ResponseResult.error();
	}

	@ApiIgnore
	@PostMapping(value="/exits",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	boolean exits(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exits(params);
	}
}
