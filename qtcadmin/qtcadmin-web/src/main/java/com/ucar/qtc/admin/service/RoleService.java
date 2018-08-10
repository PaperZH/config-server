package com.ucar.qtc.admin.service;

import com.ucar.qtc.admin.domain.RoleDO;
import com.ucar.qtc.admin.dto.UserRoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 角色服务
 * @date: 2018/8/7 10:19
 */
@Service
public interface RoleService {

	RoleDO get(Long id);

	List<RoleDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(Long id);

	List<UserRoleDTO> list(Long userId);

	int batchremove(Long[] ids);

    /**
     * 获取用户的角色id
     * @param userId
     * @return 角色id
     */
	List<Long> RoleIdsByUserId(Long userId);
}
