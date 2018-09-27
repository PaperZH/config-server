package com.ucar.qtc.admin.dao;

import com.ucar.qtc.admin.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 用户dao
 * @date: 2018/8/7 10:19
 */
@Mapper
public interface UserDao {

	UserDO get(Long userId);

	List<UserDO> getUsers(Long[] ids);
	
	List<UserDO> list(Map<String, Object> map);

	List<UserDO> getTeacherById(Long id, int relationType);

	List<UserDO> getStudentById(@Param("id") Long id,@Param("relationType") int relationType);

	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);

	int changeProfile(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();
}
