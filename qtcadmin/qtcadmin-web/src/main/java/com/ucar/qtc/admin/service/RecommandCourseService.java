package com.ucar.qtc.admin.service;

import com.ucar.qtc.admin.domain.RecommandCourseDO;

import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 */
public interface RecommandCourseService {

    RecommandCourseDO get(Long id);

    List<Long> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(RecommandCourseDO user);

    int update(RecommandCourseDO user);

    int remove(Long id);

    int batchremove(Long[] ids);

}
