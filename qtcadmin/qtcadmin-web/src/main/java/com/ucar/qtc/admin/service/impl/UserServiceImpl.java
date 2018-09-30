package com.ucar.qtc.admin.service.impl;

import com.ucar.qtc.admin.dao.DeptDao;
import com.ucar.qtc.admin.dao.UserDao;
import com.ucar.qtc.admin.dao.UserRoleDao;
import com.ucar.qtc.admin.domain.DeptDO;
import com.ucar.qtc.admin.domain.Tree;
import com.ucar.qtc.admin.domain.UserDO;
import com.ucar.qtc.admin.domain.UserRoleDO;
import com.ucar.qtc.admin.service.UserService;
import com.ucar.qtc.admin.utils.BuildTree;
import com.ucar.qtc.admin.utils.MD5Utils;
import com.ucar.qtc.admin.vo.UserVO;
import com.ucar.qtc.common.constants.CommonConstants;
import com.ucar.qtc.common.context.FilterContextHandler;
import com.ucar.qtc.common.dto.LoginUserDTO;
import com.ucar.qtc.common.service.RedisCacheService;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author: cong.zhou01
 * @description: 用户服务实现类
 * @date: 2018/8/7 10:19
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
    UserDao userMapper;

	@Autowired
    UserRoleDao userRoleMapper;

	@Autowired
    DeptDao deptMapper;

	@Autowired
	RedisCacheService redisCacheService;

	@Override
	public UserDO get(Long id) {
		List<Long> roleIds = userRoleMapper.listRoleId(id);
		UserDO user = userMapper.get(id);
		Long deptId = user.getDeptId();
		if(deptId != null && deptId != 0){
			user.setDeptName(deptMapper.get(deptId).getName());
		}
		user.setRoleIds(roleIds);
		return user;
	}

	@Override
	public List<UserDO> getUsers(Long[] ids) {
		return userMapper.getUsers(ids);
	}

	@Override
	public List<UserDO> getTeacherById(Long id, int relationType) {
		List<UserDO> users = userMapper.getTeacherById(id,relationType);
		for(UserDO userDO:users){
			userDO.setRoleIds(userRoleMapper.listRoleId(userDO.getUserId()));
			userDO.setDeptName(deptMapper.get(userDO.getDeptId()).getName());
		}
		return users;
	}

	@Override
	public List<UserDO> getStudentById(Long id, int relationType) {
		List<UserDO> users = userMapper.getStudentById(id,relationType);
		for(UserDO userDO:users){
			userDO.setRoleIds(userRoleMapper.listRoleId(userDO.getUserId()));
			userDO.setDeptName(deptMapper.get(userDO.getDeptId()).getName());
		}
		return users;
	}

	@Override
	public List<UserDO> list(Map<String, Object> map) {
		return userMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return userMapper.count(map);
	}

	@Override
	public int save(UserDO user) {
		int count = userMapper.save(user);
		Long userId = user.getUserId();
		List<Long> roles = user.getRoleIds();
		userRoleMapper.removeByUserId(userId);
		List<UserRoleDO> list = new ArrayList<>();
		for (Long roleId : roles) {
			UserRoleDO ur = new UserRoleDO();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			list.add(ur);
		}
		if (list.size() > 0) {
			userRoleMapper.batchSave(list);
		}
		return count;
	}

	@Override
	public int update(UserDO user) {
		int r = userMapper.update(user);
		Long userId = user.getUserId();
		List<Long> roles = user.getRoleIds();
		if(null!=roles){
			userRoleMapper.removeByUserId(userId);
			List<UserRoleDO> list = new ArrayList<>();
			for (Long roleId : roles) {
				UserRoleDO ur = new UserRoleDO();
				ur.setUserId(userId);
				ur.setRoleId(roleId);
				list.add(ur);
			}
			if (list.size() > 0) {
				userRoleMapper.batchSave(list);
			}
		}
		return r;
	}

	@Override
	public LoginUserDTO currentUser() {
		LoginUserDTO loginUserDTO = new LoginUserDTO();
		loginUserDTO.setId(FilterContextHandler.getUserID());
		UserDO userDo = redisCacheService.get(CommonConstants.REDIS_USER_INFO_PREFIX+
									FilterContextHandler.getUserID(), UserDO.class);
		if (userDo == null) {
			userDo = get(Long.parseLong(FilterContextHandler.getUserID().toString()));
			if (userDo == null) {
				return loginUserDTO;
			}
			redisCacheService.put(CommonConstants.REDIS_USER_INFO_PREFIX+
					FilterContextHandler.getUserID(), userDo);
		}
		loginUserDTO.setUsername(userDo.getUsername());
		loginUserDTO.setName(userDo.getName());
		loginUserDTO.setNickname(userDo.getNickname());
		loginUserDTO.setAvatar(userDo.getAvatar());
		loginUserDTO.setEmail(userDo.getEmail());
		loginUserDTO.setUserno(userDo.getUserno());
		return loginUserDTO;
	}

	@Override
	public UserDO frontCurrentUser() {
		UserDO userDo = redisCacheService.get(CommonConstants.REDIS_USER_INFO_PREFIX+
				FilterContextHandler.getUserID(), UserDO.class);
		if (userDo == null) {
			userDo = get(Long.parseLong(FilterContextHandler.getUserID().toString()));
			if (userDo == null) {
				return null;
			}
			redisCacheService.put(CommonConstants.REDIS_USER_INFO_PREFIX+
					FilterContextHandler.getUserID(), userDo);
		}
		return userDo;
	}

	@Override
	public int changeProfile(UserDO user) {
		int r = userMapper.update(user);
		return r;
	}

	@Override
	public int remove(Long userId) {
		userRoleMapper.removeByUserId(userId);
		return userMapper.remove(userId);
	}

	@Override
	public boolean exits(Map<String, Object> params) {
		boolean exits = userMapper.list(params).size() > 0;
		return exits;
	}

	@Override
	public Set<String> listRoles(Long userId) {
		return null;
	}

	@Override
	public int resetPwd(UserVO userVO, UserDO userDO) throws Exception {
		if(Objects.equals(userVO.getUserDO().getUserId(),userDO.getUserId())){
			if(Objects.equals(MD5Utils.encrypt(userDO.getUsername(),userVO.getPwdOld()),userDO.getPassword())){
				userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(),userVO.getPwdNew()));
				return userMapper.update(userDO);
			}else{
				throw new Exception("输入的旧密码有误！");
			}
		}else{
			throw new Exception("你修改的不是你登录的账号！");
		}
	}
	@Override
	public int adminResetPwd(UserVO userVO) throws Exception {
		UserDO userDO =get(userVO.getUserDO().getUserId());
		if(userDO.getUserId() == 1){
			throw new Exception("超级管理员的账号不允许直接重置！");
		}
		userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
		return userMapper.update(userDO);


	}

	@Transactional
	@Override
	public int batchremove(Long[] userIds) {
		int count = userMapper.batchRemove(userIds);
		userRoleMapper.batchRemoveByUserId(userIds);
		return count;
	}

	@Override
	public Tree<DeptDO> getTree() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> depts = deptMapper.list(new HashMap<String, Object>(16));
		Long[] pDepts = deptMapper.listParentDept();
		Long[] uDepts = userMapper.listAllDept();
		Long[] allDepts = (Long[]) ArrayUtils.addAll(pDepts, uDepts);
		for (DeptDO dept : depts) {
			if (!ArrayUtils.contains(allDepts, dept.getDeptId())) {
				continue;
			}
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(dept.getDeptId().toString());
			tree.setParentId(dept.getParentId().toString());
			tree.setText(dept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			state.put("mType", "dept");
			tree.setState(state);
			trees.add(tree);
		}
		List<UserDO> users = userMapper.list(new HashMap<String, Object>(16));
		for (UserDO user : users) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(user.getUserId().toString());
			tree.setParentId(user.getDeptId().toString());
			tree.setText(user.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			state.put("mType", "user");
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public int updatePersonal(UserDO userDO) {
		return userMapper.update(userDO);
	}

	@Override
	public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception {
		return null;
	}
}
