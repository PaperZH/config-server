package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.DeptDO;
import com.ucar.qtc.admin.domain.Tree;
import com.ucar.qtc.admin.service.DeptService;
import com.ucar.qtc.common.annotation.Log;
import com.ucar.qtc.common.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 部门管理
 * @date: 2018/8/7 10:19
 */
@Api(description = "部门管理API接口")
@RestController
@RequestMapping("/dept")
public class DeptController {

	private String prefix = "system/dept";

	@Autowired
	private DeptService deptService;

	@ApiOperation(value="获取部门列表信息", notes="获取部门列表信息")
	@Log("获取部门列表")
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Tree<DeptDO>> list() {
		return deptService.getTree().getChildren();
	}

	@ApiOperation(value="根据部门ID获取部门信息", notes="根据部门ID获取部门信息")
	@Log("根据部门ID获取部门信息")
	@GetMapping(value="{pId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	DeptDO get(@PathVariable("pId") Long pId) {
		DeptDO dept = deptService.get(pId);
		return dept;
	}

	/**
	 * 保存
	 */
	@ApiOperation(value="保存部门", notes="保存部门")
	@Log("保存部门")
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseResult save(@RequestBody DeptDO sysDept) {

		if (deptService.save(sysDept) > 0) {
			return ResponseResult.ok();
		}
		return ResponseResult.error();
	}

	/**
	 * 修改
	 */
	@ApiOperation(value="修改部门", notes="修改部门")
	@Log("修改部门")
	@PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseResult update(@RequestBody DeptDO sysDept) {
		if (deptService.update(sysDept) > 0) {
			return ResponseResult.ok();
		}
		return ResponseResult.error();
	}

	/**
	 * 删除
	 */
	@ApiOperation(value="删除部门", notes="删除部门")
	@Log("删除部门")
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseResult remove(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", id);
		if(deptService.count(map)>0) {
			return ResponseResult.error(1, "包含下级部门,不允许修改");
		}
		if(deptService.checkDeptHasUser(id)) {
			if (deptService.remove(id) > 0) {
				return ResponseResult.ok();
			}
		}else {
			return ResponseResult.error(1, "部门包含用户,不允许修改");
		}
		return ResponseResult.error();
	}

	/**
	 * 删除
	 */
	@ApiIgnore
	@Log("批量删除部门")
	@PostMapping(value="batchRemove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseResult remove(@RequestParam("ids[]") Long[] deptIds) {
		deptService.batchRemove(deptIds);
		return ResponseResult.ok();
	}

	@ApiIgnore
	@GetMapping(value="tree", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = deptService.getTree();
		return tree;
	}

	@ApiIgnore
	@GetMapping(value="treeView", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	String treeView() {
		return  prefix + "/deptTree";
	}
}
