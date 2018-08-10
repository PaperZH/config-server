package com.ucar.qtc.admin.service.impl;

import com.ucar.qtc.admin.dao.RoleDao;
import com.ucar.qtc.admin.dao.RoleMenuDao;
import com.ucar.qtc.admin.dao.UserDao;
import com.ucar.qtc.admin.dao.UserRoleDao;
import com.ucar.qtc.admin.domain.RoleDO;
import com.ucar.qtc.admin.domain.RoleMenuDO;
import com.ucar.qtc.admin.dto.UserRoleDTO;
import com.ucar.qtc.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author: cong.zhou01
 * @description: 角色服务实现类
 * @date: 2018/8/7 10:19
 */
@Service
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";

    @Autowired
    RoleDao roleMapper;
    @Autowired
    RoleMenuDao roleMenuMapper;
    @Autowired
    UserDao userMapper;
    @Autowired
    UserRoleDao userRoleMapper;

    @Override
    public List<RoleDO> list(Map<String, Object> map) {
        List<RoleDO> roles = roleMapper.list(map);
        return roles;
    }

    @Override
    public int count(Map<String, Object> map) {
        return roleMapper.count(map);
    }


    @Override
    public List<UserRoleDTO> list(Long userId) {
        List<Long> rolesIds = userRoleMapper.listRoleId(userId);
        List<RoleDO> roles = roleMapper.list(new HashMap<>(16));
        List<UserRoleDTO> roleDTOS = new ArrayList<>();
        for (RoleDO roleDO : roles) {
            UserRoleDTO userRoleDTO = new UserRoleDTO();
            userRoleDTO.setId(roleDO.getRoleId());
            userRoleDTO.setName(roleDO.getRoleName());
            userRoleDTO.setChecked(false);
            for (Long roleId : rolesIds) {
                if (Objects.equals(roleDO.getRoleId(), roleId)) {
                    // roleDO.setRoleSign("true");
                    userRoleDTO.setChecked(true);
                    break;
                }
            }
            roleDTOS.add(userRoleDTO);
        }
        return roleDTOS;
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Transactional
    @Override
    public int save(RoleDO role) {
        int count = roleMapper.save(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        List<RoleMenuDO> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return count;
    }

    @Transactional
    @Override
    public int remove(Long id) {
        int count = roleMapper.remove(id);
        roleMenuMapper.removeByRoleId(id);
        return count;
    }

    @Override
    public RoleDO get(Long id) {
        RoleDO roleDO = roleMapper.get(id);
        return roleDO;
    }

    @Override
    public int update(RoleDO role) {
        int r = roleMapper.update(role);
        List<Long> menuIds = role.getMenuIds();
        if(null!=menuIds){
            Long roleId = role.getRoleId();
            roleMenuMapper.removeByRoleId(roleId);
            List<RoleMenuDO> rms = new ArrayList<>();
            for (Long menuId : menuIds) {
                RoleMenuDO rmDo = new RoleMenuDO();
                rmDo.setRoleId(roleId);
                rmDo.setMenuId(menuId);
                rms.add(rmDo);
            }
            if (rms.size() > 0) {
                roleMenuMapper.batchSave(rms);
            }
        }
        return r;
    }

    @Override
    public int batchremove(Long[] ids) {
        int r = roleMapper.batchRemove(ids);
        return r;
    }

    /**
     * 获取用户的角色id
     *
     * @param userId
     * @return 角色id
     */
    @Override
    public List<Long> RoleIdsByUserId(Long userId) {
        return roleMapper.roleIdsByUserId(userId);
    }

}
