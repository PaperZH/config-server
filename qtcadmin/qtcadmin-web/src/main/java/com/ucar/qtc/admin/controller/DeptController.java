package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.DeptDO;
import com.ucar.qtc.admin.domain.Tree;
import com.ucar.qtc.admin.service.DeptService;
import com.ucar.qtc.common.utils.ResponseResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
public class DeptController extends BaseController {
	private String prefix = "system/dept";
	@Autowired
	private DeptService sysDeptService;

	@GetMapping()
	@RequiresPermissions("system:sysDept:sysDept")
	String dept() {
		return prefix + "/dept";
	}

	@ResponseBody
	@GetMapping("/list")
	public List<DeptDO> list() {
		Map<String, Object> query = new HashMap<>(16);
		List<DeptDO> sysDeptList = sysDeptService.list(query);
		return sysDeptList;
	}

	@GetMapping("/add/{pId}")
	@RequiresPermissions("system:sysDept:add")
	String add(@PathVariable("pId") Long pId, Model model) {
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "总部门");
		} else {
			model.addAttribute("pName", sysDeptService.get(pId).getName());
		}
		return  prefix + "/add";
	}

//	@GetMapping("/edit/{deptId}")
//	@RequiresPermissions("system:sysDept:edit")
//	String edit(@PathVariable("deptId") Long deptId, Model model) {
//		DeptDO sysDept = sysDeptService.get(deptId);
//		model.addAttribute("sysDept", sysDept);
//		if(Constant.DEPT_ROOT_ID.equals(sysDept.getParentId())) {
//			model.addAttribute("parentDeptName", "无");
//		}else {
//			DeptDO parDept = sysDeptService.get(sysDept.getParentId());
//			model.addAttribute("parentDeptName", parDept.getName());
//		}
//		return  prefix + "/edit";
//	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:sysDept:add")
	public ResponseResult save(DeptDO sysDept) {
		if (sysDeptService.save(sysDept) > 0) {
			return ResponseResult.ok();
		}
		return ResponseResult.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:sysDept:edit")
	public ResponseResult update(DeptDO sysDept) {
		if (sysDeptService.update(sysDept) > 0) {
			return ResponseResult.ok();
		}
		return ResponseResult.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:remove")
	public ResponseResult remove(Long deptId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", deptId);
		if(sysDeptService.count(map)>0) {
			return ResponseResult.error(1, "包含下级部门,不允许修改");
		}
		if(sysDeptService.checkDeptHasUser(deptId)) {
			if (sysDeptService.remove(deptId) > 0) {
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
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:batchRemove")
	public ResponseResult remove(@RequestParam("ids[]") Long[] deptIds) {
		sysDeptService.batchRemove(deptIds);
		return ResponseResult.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = sysDeptService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/deptTree";
	}

}
