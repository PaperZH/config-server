package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.UserDO;
import com.ucar.qtc.admin.dto.UserDTO;
import com.ucar.qtc.admin.dto.do2dto.UserConvert;
import com.ucar.qtc.admin.service.RoleService;
import com.ucar.qtc.admin.service.UserService;
import com.ucar.qtc.admin.utils.MD5Utils;
import com.ucar.qtc.common.annotation.Log;
import com.ucar.qtc.common.context.FilterContextHandler;
import com.ucar.qtc.common.dto.LoginUserDTO;
import com.ucar.qtc.common.utils.PageUtils;
import com.ucar.qtc.common.utils.Query;
import com.ucar.qtc.common.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 用户管理
 * @date: 2018/8/7 10:19
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

	/**
	 * 登录的当前用户，前台需要验证用户登录的页面可以调用此方法
	 * @return
	 */
    @GetMapping("/currentUser")
	LoginUserDTO currentUser(){
		LoginUserDTO loginUserDTO = new LoginUserDTO();
		loginUserDTO.setId(FilterContextHandler.getUserID());
		loginUserDTO.setUsername(FilterContextHandler.getUsername());
		loginUserDTO.setName(FilterContextHandler.getName());
		loginUserDTO.setNickname(FilterContextHandler.getNickname());
		return loginUserDTO;
	}

	/**
	 * 根据用户id获取用户
	 * @param id
	 * @return
	 */
    @GetMapping("{id}")
    ResponseResult get(@PathVariable("id") Long id ){
		UserDTO userDTO = UserConvert.MAPPER.do2dto(userService.get(id));
    	return ResponseResult.ok().put("data",userDTO);
	}

	/**
	 * 分页查询用户
	 * @param params
	 * @return
	 */
	@Log("获取用户列表")
    @GetMapping()
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
	@PostMapping()
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
	@PutMapping()
    ResponseResult update(@RequestBody UserDO user) {
		return ResponseResult.operate(userService.update(user) > 0);
	}


	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@DeleteMapping()
    ResponseResult remove(Long id) {
		return ResponseResult.operate (userService.remove(id) > 0);
	}

	@PostMapping("/batchRemove")
	@ResponseBody
    ResponseResult batchRemove(@RequestParam("ids[]") Long[] userIds) {
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return ResponseResult.ok();
		}
		return ResponseResult.error();
	}

	@PostMapping("/exits")
	@ResponseBody
	boolean exits(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exits(params);
	}
}
