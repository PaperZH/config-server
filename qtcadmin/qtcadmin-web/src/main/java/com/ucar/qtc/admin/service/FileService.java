package com.ucar.qtc.admin.service;

import com.ucar.qtc.admin.domain.FileDO;

import java.util.List;
import java.util.Map;

/**
 * @author cong.zhou01
 * @name FileService
 * @description 文件服务
 * @date 2018-08-16 10:20
 */
public interface FileService {

    FileDO get(Long id);

    List<FileDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(FileDO file);

    int update(FileDO file);

    int remove(Long id);

    int batchRemove(Long[] ids);
}