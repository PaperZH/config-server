package com.ucar.qtc.admin.dao;

import com.ucar.qtc.admin.domain.RecommandCourseDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 */
@Mapper
public interface RecommandCourseDao {

    RecommandCourseDO get(Long id);

    List<RecommandCourseDO> list(Map<String, Object> map);

    List<Long> listCourseIds(Map<String,Object> map);

    int count(Map<String, Object> map);

    int save(RecommandCourseDO recommandCourseDO);

    int update(RecommandCourseDO recommandCourseDO);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
