package com.ucar.qtc.admin.service.impl;

import com.ucar.qtc.admin.dao.RecommandCourseDao;
import com.ucar.qtc.admin.dao.UserDao;
import com.ucar.qtc.admin.domain.RecommandCourseDO;
import com.ucar.qtc.admin.rpc.CourseServiceRpc;
import com.ucar.qtc.admin.service.RecCourseService;
import com.ucar.qtc.admin.service.UserService;
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

    @Autowired
    private UserService userService;

    @Override
    public RecommandCourseDO get(Long id) {
        return recommandCourseDao.get(id);
    }

    @Override
    public List<RecommandCourseDO> list(Map<String, Object> map) {
        return recommandCourseDao.list(map);
    }

    @Override
    public ResponseResult listRecCourseByQuery(QueryVO queryVO){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("currentPage",(queryVO.getCurrentPage()-1)*queryVO.getPageSize());
        map.put("pageSize",queryVO.getPageSize());
        List<RecommandCourseDO> recommandCourseList = recommandCourseDao.list(map);
        if(recommandCourseList.size() == 0){
            return ResponseResult.error();
        }
        return getRecCourseList(recommandCourseList);
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
        return recommandCourseDao.batchRemove(ids);
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
            Integer tempId = (Integer) tempMap.get("courseId");
            if(tempId!=null){
                ids.add((tempId).longValue());
            }
        }
        resMap.put("ids",ids);
        return resMap;
    }
    /**
     * @param recommandCourseList 推荐课程列表
     * @return
     */
    private ResponseResult getRecCourseList(List<RecommandCourseDO> recommandCourseList){
        //推荐课程列表总数
        int total = recommandCourseDao.count(null);
        //获取推荐课程列表的id数组
        int size = recommandCourseList.size();
        Long[] ids = new Long[size];
        Iterator iterator = recommandCourseList.iterator();
        int index = 0;
        while(iterator.hasNext()){
            ids[index++] = ((RecommandCourseDO)iterator.next()).getCourseId();
        }
        //将推荐课程的id存放到QueryVO
        QueryVO queryVO = new QueryVO();
        queryVO.setCourseIds(ids);
        //通过调用assist服务，得到推荐课程详细信息
        List<CourseVO> recCourseVOS = new ArrayList<CourseVO>();
        recCourseVOS = courseService.getRecCourseList(queryVO).getRe();
        List<CourseVO> reccomandCourseVO = new ArrayList<CourseVO>();
        //按照ord_num的顺序，将数据填充
        for(int i = 0;i < ids.length;i ++){
            iterator = recCourseVOS.iterator();
            while(iterator.hasNext()){
                CourseVO temp = (CourseVO) iterator.next();
                if(temp.getCourseId()== ids[i])
                {
                    temp.setOrderNum(recommandCourseList.get(i).getOrderNum());
                    temp.setRecCourseInfo(recommandCourseList.get(i).getDescription());
                    String teacherName = (userService.get(temp.getTeacherId())).getNickname();
                    temp.setTeacherName(teacherName);
                    reccomandCourseVO.add(temp);
                }
            }
        }
        return ResponseResult.ok().put("total",total)
                                    .put("list",reccomandCourseVO);
    }
}
