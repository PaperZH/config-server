package com.ucar.qtc.admin.service;

import com.ucar.qtc.admin.domain.RecommandCourseDO;
import com.ucar.qtc.admin.vo.CourseVO;
import com.ucar.qtc.admin.vo.QueryVO;

import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 */
public interface RecCourseService {

    RecommandCourseDO get(Long id);

    List<RecommandCourseDO> list(Map<String, Object> map);

    public List<CourseVO> listRecCourseByQuery(QueryVO queryVO);

    int count(Map<String, Object> map);

    int save(RecommandCourseDO user);

    int update(RecommandCourseDO user);

    int remove(Long id);

    int batchremove(Long[] ids);

}
