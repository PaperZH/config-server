package com.ucar.qtc.base.dao;

import com.ucar.qtc.base.dto.LogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 基础日志服务dao层
 * @date: 2018/8/7 10:19
 */
@Mapper
public interface LogDao {

	LogDO get(Long id);
	
	List<LogDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(LogDO log);
	
	int update(LogDO log);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
