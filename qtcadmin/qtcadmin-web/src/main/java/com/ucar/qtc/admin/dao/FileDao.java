package com.ucar.qtc.admin.dao;

import com.ucar.qtc.admin.domain.FileDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author cong.zhou01
 * @name FileDao
 * @description 文件上传
 * @date 2018-08-16 10:21
 */
@Mapper
public interface FileDao {
    FileDO get(Long id);

    List<FileDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(FileDO file);

    int update(FileDO file);

    int remove(Long id);

    int batchRemove(Long[] ids);
}


