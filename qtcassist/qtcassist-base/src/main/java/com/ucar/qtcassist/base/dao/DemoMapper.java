package com.ucar.qtcassist.base.dao;

import com.ucar.qtcassist.base.model.Demo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Demo record);

    int insertSelective(Demo record);

    @Cacheable(cacheNames = "latest.demo")
    Demo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Demo record);

    int updateByPrimaryKey(Demo record);
}