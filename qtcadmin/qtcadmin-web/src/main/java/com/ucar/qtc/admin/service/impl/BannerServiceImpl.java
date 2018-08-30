package com.ucar.qtc.admin.service.impl;

import com.ucar.qtc.admin.dao.BannerDao;
import com.ucar.qtc.admin.domain.BannerDO;
import com.ucar.qtc.admin.dto.BannerDTO;
import com.ucar.qtc.admin.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService{

    @Autowired
    private BannerDao bannerDao;

    @Override
    public BannerDO get(Long id) {
        return bannerDao.get(id);
    }

    @Override
    public List<BannerDO> list(Map<String, Object> map) {
        return bannerDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return bannerDao.count(map);
    }

    @Override
    public int save(BannerDO file) {
        return bannerDao.save(file);
    }

    @Override
    public int update(BannerDO file) {
        return bannerDao.update(file);
    }

    @Override
    public int remove(Long id) {
        return bannerDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return bannerDao.batchRemove(ids);
    }
}
