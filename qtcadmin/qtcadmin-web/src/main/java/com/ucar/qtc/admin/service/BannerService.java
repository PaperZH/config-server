package com.ucar.qtc.admin.service;

import com.ucar.qtc.admin.domain.BannerDO;
import com.ucar.qtc.admin.dto.BannerDTO;

import java.util.List;
import java.util.Map;

public interface BannerService {
    BannerDO get(Long id);

    List<BannerDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(BannerDO file);

    int update(BannerDO file);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
