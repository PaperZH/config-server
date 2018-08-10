package com.ucar.qtc.base.service.impl;


import com.ucar.qtc.base.dao.LogDao;
import com.ucar.qtc.base.service.LogService;
import com.ucar.qtc.common.dto.LogDO;
import com.ucar.qtc.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: cong.zhou01
 * @description: 基础日志服务实现类
 * @date: 2018/8/7 10:19
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogDao logMapper;

    @Async
    @Override
    public int save(LogDO logDO) {
        return logMapper.save(logDO);
    }

    @Override
    public List<LogDO> queryList(Query query) {
        List<LogDO> logs = logMapper.list(query);
        return logs;
    }

    @Override
    public int count(Query query) {
        return logMapper.count(query);
    }

    @Override
    public int remove(Long id) {
        int count = logMapper.remove(id);
        return count;
    }

    @Override
    public int batchRemove(Long[] ids) {
        return logMapper.batchRemove(ids);
    }
}
