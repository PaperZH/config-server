package com.ucar.qtc.admin.dao;

import com.ucar.qtc.admin.domain.MenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 菜单管理dao
 * @date: 2018/8/7 10:19
 */
@Mapper
public interface MenuDao {

	MenuDO get(Long menuId);
	
	List<MenuDO> list(Map<String, Object> map);

	List<MenuDO> listFront(Map<String, Object> map);

	List<MenuDO> listBack(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(MenuDO menu);
	
	int update(MenuDO menu);
	
	int remove(Long menuId);
	
	int batchRemove(Long[] menuIds);
	
	List<MenuDO> listMenuByUserId(Long id);

	List<MenuDO> listFrontMenuByUserId(Long id);

	List<MenuDO> listBackMenuByUserId(Long id);

	
	List<String> listUserPerms(Long id);
}
