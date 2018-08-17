package com.ucar.qtc.base.service;

import com.ucar.qtc.base.dto.LogDO;
import com.ucar.qtc.base.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: cong.zhou01
 * @description: 基础日志服务
 * @date: 2018/8/7 10:19
 */
@Service
public interface LogService {
    int save(LogDO logDO);

    List<LogDO> queryList(Query query);

    int count(Query query);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
