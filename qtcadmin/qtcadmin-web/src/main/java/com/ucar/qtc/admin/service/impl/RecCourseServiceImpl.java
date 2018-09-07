package com.ucar.qtc.admin.service.impl;

import com.ucar.qtc.admin.dao.RecommandCourseDao;
import com.ucar.qtc.admin.domain.RecommandCourseDO;
import com.ucar.qtc.admin.rpc.CourseServiceRpc;
import com.ucar.qtc.admin.service.RecCourseService;
import com.ucar.qtc.admin.vo.CourseVO;
import com.ucar.qtc.admin.vo.QueryVO;
import com.ucar.qtc.common.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
public class RecCourseServiceImpl implements RecCourseService {

    @Autowired
    private RecommandCourseDao recommandCourseDao;

    @Autowired
    private CourseServiceRpc courseService;

    @Override
    public RecommandCourseDO get(Long id) {
        return recommandCourseDao.get(id);
    }

    @Override
    public List<RecommandCourseDO> list(Map<String, Object> map) {
        return recommandCourseDao.list(map);
    }

    @Override
    public List<CourseVO> listRecCourseByQuery(QueryVO queryVO){
        List<Long> listCourseIds = new ArrayList<Long>();

        //首先通过名字查询课程信息
        List courseVOS = (List) courseService.getCourseIdAndCourseName(queryVO).get("ids");
        if(courseVOS==null||courseVOS.isEmpty()){
            return null;
        }

        Map<String,Object> resMap = new HashMap<String,Object>();
        Iterator iterator = courseVOS.iterator();
        //取出课程信息ID
        while(iterator.hasNext()){
            Map tempMap = (Map)iterator.next();
            listCourseIds.add(((Integer) tempMap.get("id")).longValue());
        }
        resMap.put("ids",listCourseIds);

        List<RecommandCourseDO> recommandCourseList = recommandCourseDao.list(resMap);
        if(recommandCourseList.size() == 0){
            return null;
        }
        Long[] ids = new Long[recommandCourseList.size()];
        iterator = recommandCourseList.iterator();
        int index = 0;
        while(iterator.hasNext()){
            ids[index++] = ((RecommandCourseDO)iterator.next()).getCourseId();
        }
        List<CourseVO> recCourseVOS = new ArrayList<CourseVO>();
        queryVO.setCourseIds(ids);
        recCourseVOS = courseService.getRecCourseList(queryVO).getRe();
        List<CourseVO> result = new ArrayList<CourseVO>();

        //按照ord_num的顺序，将数据填充
        for(int i = 0;i < ids.length;i ++){
            iterator = recCourseVOS.iterator();
            while(iterator.hasNext()){
                CourseVO temp = (CourseVO) iterator.next();
                if(temp.getCourseId() == ids[i]){
                    temp.setOrderNum(recommandCourseList.get(i).getOrderNum());
                    temp.setRecCourseInfo(recommandCourseList.get(i).getDescription());
                    result.add(temp);
                }
            }

        }
        return result;
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
