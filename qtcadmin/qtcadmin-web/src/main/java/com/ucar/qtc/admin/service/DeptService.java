package com.ucar.qtc.admin.service;

import com.ucar.qtc.admin.domain.Tree;
import com.ucar.qtc.admin.domain.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 部门服务
 * @date: 2018/8/7 10:19
 */
public interface DeptService {
	
	DeptDO get(Long deptId);
	
	List<DeptDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptDO sysDept);
	
	int update(DeptDO sysDept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);

	Tree<DeptDO> getTree();
	
	boolean checkDeptHasUser(Long deptId);
}
