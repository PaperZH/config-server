package com.ucar.qtc.admin.service.impl;

import com.ucar.qtc.admin.dao.FileDao;
import com.ucar.qtc.admin.domain.FileDO;
import com.ucar.qtc.admin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cong.zhou01
 * @name FileServiceImpl
 * @description TODO
 * @date 2018-08-16 10:20
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileDao fileDao;

    @Override
    public FileDO get(Long id){
        return fileDao.get(id);
    }

    @Override
    public List<FileDO> list(Map<String, Object> map){
        return fileDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map){
        return fileDao.count(map);
    }

    @Override
    public int save(FileDO file){
        return fileDao.save(file);
    }

    @Override
    public int update(FileDO file){
        return fileDao.update(file);
    }

    @Override
    public int remove(Long id){
        return fileDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids){
        return fileDao.batchRemove(ids);
    }

}

