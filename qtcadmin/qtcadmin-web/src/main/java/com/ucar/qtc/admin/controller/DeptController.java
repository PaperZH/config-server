package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.DeptDO;
import com.ucar.qtc.admin.domain.Tree;
import com.ucar.qtc.admin.service.DeptService;
import com.ucar.qtc.common.annotation.Log;
import com.ucar.qtc.common.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 部门管理
 * @date: 2018/8/7 10:19
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
	private String prefix = "system/dept";
	@Autowired
	private DeptService deptService;

	@Log("获取部门列表")
	@GetMapping
	public List<Tree<DeptDO>> list() {
		return deptService.getTree().getChildren();
	}

	@GetMapping("{pId}")
	DeptDO get(@PathVariable("pId") Long pId) {
		DeptDO dept = deptService.get(pId);
		return dept;
	}

	/**
	 * 保存
	 */
	@Log("保存部门")
	@PostMapping
	public ResponseResult save(@RequestBody DeptDO sysDept) {

		if (deptService.save(sysDept) > 0) {
			return ResponseResult.ok();
		}
		return ResponseResult.error();
	}

	/**
	 * 修改
	 */
	@Log("修改部门")
	@PutMapping
	public ResponseResult update(@RequestBody DeptDO sysDept) {
		if (deptService.update(sysDept) > 0) {
			return ResponseResult.ok();
		}
		return ResponseResult.error();
	}

	/**
	 * 删除
	 */
	@Log("删除部门")
	@DeleteMapping
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
	@Log("批量删除部门")
	@PostMapping("batchRemove")
	public ResponseResult remove(@RequestParam("ids[]") Long[] deptIds) {
		deptService.batchRemove(deptIds);
		return ResponseResult.ok();
	}

	@GetMapping("tree")
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = deptService.getTree();
		return tree;
	}

	@GetMapping("treeView")
	String treeView() {
		return  prefix + "/deptTree";
	}

}
