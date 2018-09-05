package com.ucar.qtc.admin.service.impl;

import com.ucar.qtc.admin.dao.RecommandCourseDao;
import com.ucar.qtc.admin.domain.RecommandCourseDO;
import com.ucar.qtc.admin.service.RecommandCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @param: $param$
 * @return: $return$
 * @auther: $user$
 * @date: $date$ $time$
 */
@Service
public class RecommandCourseServiceImpl implements RecommandCourseService{

    @Autowired
    private RecommandCourseDao recommandCourseDao;

    @Override
    public RecommandCourseDO get(Long id) {
        return recommandCourseDao.get(id);
    }

    @Override
    public List<Long> list(Map<String, Object> map) {
        return recommandCourseDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return recommandCourseDao.count(map);
    }

    @Override
    public int save(RecommandCourseDO recommandCourseDO) {
        return recommandCourseDao.save(recommandCourseDO);
    }

    @Override
    public int update(RecommandCourseDO recommandCourseDO) {
        return recommandCourseDao.update(recommandCourseDO);
    }

    @Override
    public int remove(Long id) {
        return recommandCourseDao.remove(id);
    }

    @Override
    public int batchremove(Long[] ids) {
        return recommandCourseDao.batchremove(ids);
    }
}
