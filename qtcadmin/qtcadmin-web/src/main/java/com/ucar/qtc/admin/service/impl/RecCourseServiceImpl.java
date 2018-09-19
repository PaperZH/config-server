package com.ucar.qtc.admin.service.impl;

import com.ucar.qtc.admin.dao.RecommandCourseDao;
import com.ucar.qtc.admin.domain.RecommandCourseDO;
import com.ucar.qtc.admin.rpc.CourseServiceRpc;
import com.ucar.qtc.admin.service.RecCourseService;
import com.ucar.qtc.admin.vo.CourseVO;
import com.ucar.qtc.admin.vo.QueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * author：guodong.zhang
 * 功能描述：
 * 对推荐课程的Service类的实现
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
        //首先通过名字查询课程信息
        List courses = (List) courseService.getCourseIdAndCourseName(queryVO).get("ids");
        if(courses==null||courses.isEmpty()){
            return null;
        }
        //通过查询课程的ID，查询推荐课程
        Map<String,Object> courseResMap = getCoursesMap(courses);
        List<RecommandCourseDO> recommandCourseList = recommandCourseDao.list(courseResMap);
        if(recommandCourseList.size() == 0){
            return null;
        }
        return getRecCourseList(recommandCourseList,courses);
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

    /**
     * 获得Course的map结构信息
     * @param courseList 查询的课程列表信息
     * @return
     */
    private Map<String,Object> getCoursesMap(List courseList){
        List<Long> ids = new ArrayList<Long>();
        Map<String,Object> resMap = new HashMap<String,Object>();
        Iterator iterator = courseList.iterator();
        //取出课程信息ID
        while(iterator.hasNext()){
            Map tempMap = (Map)iterator.next();
            ids.add(((Integer) tempMap.get("id")).longValue());
        }
        resMap.put("ids",ids);
        return resMap;
    }
    /**
     * @param recommandCourseList 推荐课程列表
     * @param courseVOS 课程信息列表
     * @return
     */
    private List<CourseVO> getRecCourseList(List<RecommandCourseDO> recommandCourseList,List courseVOS){
        Long[] ids = new Long[recommandCourseList.size()];
        Iterator iterator = recommandCourseList.iterator();
        int index = 0;
        while(iterator.hasNext()){
            ids[index++] = ((RecommandCourseDO)iterator.next()).getCourseId();
        }
        QueryVO queryVO = new QueryVO();
        queryVO.setCourseIds(ids);
        List<CourseVO> recCourseVOS = new ArrayList<CourseVO>();
        recCourseVOS = courseService.getRecCourseList(queryVO).getRe();
        List<CourseVO> reccomandCourseVO = new ArrayList<CourseVO>();
        //按照ord_num的顺序，将数据填充
        for(int i = 0;i < ids.length;i ++){
            iterator = recCourseVOS.iterator();
            while(iterator.hasNext()){
                CourseVO temp = (CourseVO) iterator.next();
                if(temp.getCourseId() == ids[i]){
                    temp.setOrderNum(recommandCourseList.get(i).getOrderNum());
                    temp.setRecCourseInfo(recommandCourseList.get(i).getDescription());
                    reccomandCourseVO.add(temp);
                }
            }
        }
        return reccomandCourseVO;
    }
}
