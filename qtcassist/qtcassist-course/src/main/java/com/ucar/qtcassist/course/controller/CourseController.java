package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.CourseApi;
import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.CoursewareDTO;
import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.DO.CourseTypeDO;
import com.ucar.qtcassist.api.model.DO.EvaluateCourseDO;
import com.ucar.qtcassist.api.model.DO.QueryDO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.VO.*;
import com.ucar.qtcassist.base.common.constant.UrlPrefixConstant;
import com.ucar.qtcassist.course.model.UserDTO;
import com.ucar.qtcassist.course.service.AdminFeginClient;
import com.ucar.qtcassist.course.service.CollectCourseService;
import com.ucar.qtcassist.course.service.CourseCoursewareService;
import com.ucar.qtcassist.course.service.CourseService;
import com.ucar.qtcassist.course.service.CourseTypeService;
import com.ucar.qtcassist.course.service.EvaluateCourseService;
import com.ucar.qtcassist.course.util.CourseConvertUtil;
import com.ucar.qtcassist.course.util.QueryConvertUtil;
import com.ucar.qtcassist.courseware.service.CoursewareService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController implements CourseApi {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTypeService courseTypeService;

    @Autowired
    private CourseCoursewareService courseCoursewareService;

    @Autowired
    private CoursewareService coursewareService;

    @Autowired
    private AdminFeginClient adminFeginClient;

    @Autowired
    private CollectCourseService collectCourseService;

    @Autowired
    private EvaluateCourseService evaluateCourseService;

    /**
     * 根据类型获取分页后的课程列表(有效课程)
     * @param queryVO (String courseName, Integer currentPage, Integer pageSize, String type, Boolean isInValidDate)
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Integer currentPage 分页查询的当前页
     * Integer pageSize 分布查询的每页的记录数目
     * String type 查询的排序类型，default（默认）, time(发布时间降序), hot(点赞数量降序)
     * Boolean isInValidDate 课程状态，true：有效课程（未删除未过期）， false：未删除课程（未删除）， null：所有课程
     * @return
     */
    @Override
    public Result<Page<CourseVO>> getCourseList(@RequestBody QueryVO queryVO) {
        QueryDO queryDO = QueryConvertUtil.convertToQueryDO(queryVO);
        //根据courseName统计记录的总数
        Integer total = courseService.getTotalByIdListAndCondition(null, queryDO);
        if(total == 0) {
            return PageResult.getSuccessResult(null, total);
        } else {
            List<CourseDO> courseDOList = null;
            // 根据courseName, startIndex, pageSize, orderType等条件查询课程页列表
            courseDOList = courseService.getList(queryDO);

            List<CourseVO> courseVOList = new ArrayList<CourseVO>();
            for (CourseDO courseDO : courseDOList) {
                CourseTypeDO courseType = courseTypeService.selectByPrimaryKey(courseDO.getTypeId());
                CourseVO courseVO = CourseConvertUtil.convertToCourseVO(courseDO);

                courseVO.setTypeName(courseType.getTypeName());
                Integer collectNum = collectCourseService.getTotalByCourseId(courseVO.getCourseId());
                courseVO.setCollectNum(collectNum);

                courseVOList.add(courseVO);
            }
            return PageResult.getSuccessResult(courseVOList, total);
        }
    }

    /**
     * 根据查询条件查询某个教师发布的课程（包括有效课程、未删除课程）
     * @param queryVO (long userId, String courseName, Date startDate, Date endDate, int currentPage, int pageSize)
     * Long userId 课程的teacherId
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Date startDate 课程发布的起始时间
     * Date endDate 课程发布的结束时间
     * Integer currentPage 分页查询的当前页
     * Integer pageSize 分布查询的每页的记录数目
     * Boolean isInValidDate 课程状态，true：有效课程（未删除未过期）， false：未删除课程（未删除）， null：所有课程
     * @return
     */
    @Override
    public Result<Page<CourseVO>> getPublishCourseList(@RequestBody QueryVO queryVO) {
        QueryDO queryDO = QueryConvertUtil.convertToQueryDO(queryVO);
        //根据courseName统计记录的总数
        Integer total = courseService.getTotalByIdListAndCondition(null, queryDO);
        if(total == 0) {
            return PageResult.getSuccessResult(null, total);
        } else {
            List<CourseDO> courseDOList = null;
            // 根据courseName, startIndex, pageSize, orderType等条件查询课程页列表
            courseDOList = courseService.getListByCondition(queryDO);

            List<CourseVO> courseVOList = new ArrayList<CourseVO>();
            for (CourseDO courseDO : courseDOList) {
                CourseTypeDO courseType = courseTypeService.selectByPrimaryKey(courseDO.getTypeId());
                CourseVO courseVO = CourseConvertUtil.convertToCourseVO(courseDO);

                courseVO.setTypeName(courseType.getTypeName());
                Integer collectNum = collectCourseService.getTotalByCourseId(courseVO.getCourseId());
                courseVO.setCollectNum(collectNum);

                courseVOList.add(courseVO);
            }
            return PageResult.getSuccessResult(courseVOList, total);
        }
    }

    /**
     * 获取推荐课程列表（包括有效课程、未删除课程、所有课程）
     * @param queryVO(courseIds, courseName, currentPage, pageSize)
     * Long[] courseIds 要匹配的所有课程的id数组
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有的课程）
     * Integer currentPage 分页查询的当前页（可以为null，表示查询所有的）
     * Integer pageSize 分布查询的每页的记录数目（可以为null，表示查询所有的）
     * Boolean isInValidDate 课程状态，true：有效课程（未删除未过期）， false：未删除课程（未删除）， null：所有课程
     * @return
     */
    @Override
    public Result<List<CourseVO>> getRecCourseList(@RequestBody QueryVO queryVO) {
        QueryDO queryDO = QueryConvertUtil.convertToQueryDO(queryVO);
        List<CourseVO> courseVOList = new ArrayList<CourseVO>();

        if(queryDO.getCourseIds() == null || queryDO.getCourseIds().length == 0) {
            return Result.getSuccessResult(courseVOList);
        }

        // 根据courseIdList查询推荐课程的列表
        List<CourseDO> courseDOList = courseService.getListByCondition(queryDO);
        for (CourseDO courseDO : courseDOList) {
            CourseTypeDO courseType = courseTypeService.selectByPrimaryKey(courseDO.getTypeId());
            CourseVO courseVO = CourseConvertUtil.convertToCourseVO(courseDO);

            courseVO.setTypeName(courseType.getTypeName());
            Integer collectNum = collectCourseService.getTotalByCourseId(courseVO.getCourseId());
            courseVO.setCollectNum(collectNum);

            courseVOList.add(courseVO);
        }
        return Result.getSuccessResult(courseVOList);
    }

    /**
     * 获取所有在有效期内的课程的id、courseName、courseDescription
     * @param queryVO (String courseName, Integer currentPage, Integer pageSize)
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Integer currentPage 分页查询的当前页(可以为null)
     * Integer pageSize 分布查询的每页的记录数目(可以为null)
     * @return
     */
    @Override
    public Map<String, Object> getCourseIdAndCourseName(@RequestBody QueryVO queryVO) {
        QueryDO queryDO = QueryConvertUtil.convertToQueryDO(queryVO);
        Map<String, Object> res = new HashMap<String,Object>();
        res.put("ids",courseService.getCourseIdAndCourseName(queryDO));
        return res;
    }

    /**
     * 根据课程ID获取课程详细信息，包括课程基本信息+教师信息+课件信息
     * @param courseId 要查询的课程的id
     * @return
     */
    @Override
    public Result<CourseDetailVO<CoursewareDTO>> getCourseDetail(@PathVariable("courseId") Long courseId) {
        Map<String, Object> data = new HashMap<String, Object>();
        CourseDetailVO courseDetail = new CourseDetailVO();

        CourseDO courseDO = courseService.selectByPrimaryKey(courseId);
        CourseTypeDO courseTypeDO = courseTypeService.selectByPrimaryKey(courseDO.getTypeId());
        CourseVO courseVO = CourseConvertUtil.convertToCourseVO(courseDO);

        courseVO.setCourseType(courseTypeDO);
        courseVO.setCourseDescription(courseDO.getCourseDescription());
        Integer collectNum = collectCourseService.getTotalByCourseId(courseId);
        courseVO.setCollectNum(collectNum);

        courseDetail.setCourse(courseVO);

        String resultStr = adminFeginClient.getUserInfoById(courseDO.getTeacherId());

        JSONObject jsonObject= (JSONObject) JSONObject.fromObject(resultStr).get("data");
        UserDTO user = (UserDTO)JSONObject.toBean(jsonObject, UserDTO.class);

        TeacherVO teacher = new TeacherVO();
        teacher.setUserId(user.getUserId());
        teacher.setNickname(user.getNickname());
        teacher.setAvatar(user.getAvatar());
        teacher.setEmail(user.getEmail());
        courseDetail.setTeacher(teacher);

        List<Long> coursewareIdList = courseCoursewareService.getCoursewareIdListByCourseId(courseId);
        if(coursewareIdList == null || coursewareIdList.size() == 0) {
            courseDetail.setCoursewares(null);
        } else {
            List<CoursewareDTO> coursewareDTOList = coursewareService.selectCoursewareList(coursewareIdList);
            String preUrl = null;
            String sourceUrl = null;
            for(CoursewareDTO coursewareDTO : coursewareDTOList) {
                preUrl = UrlPrefixConstant.COURSEWARE_URL_PREFIX + coursewareDTO.getPreUrl();
                sourceUrl = UrlPrefixConstant.COURSEWARE_URL_PREFIX + coursewareDTO.getSourceUrl();
                coursewareDTO.setPreUrl(preUrl);
                coursewareDTO.setSourceUrl(sourceUrl);
            }
            courseDetail.setCoursewares(coursewareDTOList);
        }

        List<EvaluateCourseDO> evaluates = evaluateCourseService.getListByCourseId(courseId);
        courseDetail.setEvaluates(evaluates);

        return Result.getSuccessResult(courseDetail);
    }

    /**
     * 增加课程
     * @param courseVO (long userId , CourseVO courseVO)
     * @return
     */
    @Override
    public Result<CourseVO> addCourse(@RequestBody CourseVO courseVO) {
        CourseDO courseDO = CourseConvertUtil.convertToCourseDO(courseVO);

        Date date = new Date();
        courseDO.setPublishTime(date);
        courseDO.setUpdateTime(date);
        courseDO.setReadNum(0);
        courseDO.setPraiseNum(0);

        if(courseDO.getInvalidDate() == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(date);
            String year = String.valueOf(Integer.valueOf(dateStr.substring(0,4)) + 10);
            String invalidDateStr = year.concat(dateStr.substring(4));
            Date invalidDate = null;
            try {
                invalidDate = sdf.parse(invalidDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            courseDO.setInvalidDate(invalidDate);
        }

        int count = courseService.insertSelective(courseDO);
        if(count > 0) {
            return Result.getSuccessResult(courseVO);
        }
        return Result.getBusinessException("添加课程失败","");
    }

    /**
     * 用户更新课程
     * @param courseVO (long userId , CourseVO courseVO)
     * @return
     */
    @Override
    public Result<CourseVO> updateCourse(@RequestBody CourseVO courseVO) {
        CourseDO courseDO = CourseConvertUtil.convertToCourseDO(courseVO);
        courseDO.setUpdateTime(new Date());
        int count = courseService.updateByPrimaryKeySelective(courseDO);
        if(count >= 0) {
            return Result.getSuccessResult(courseVO);
        }
        return Result.getBusinessException("更新课程失败","");
    }

    /**
     * 根据课程ID批量删除课程
     * @param queryVO (long[] courseIds)
     * @return
     */
    @Override
    public Result deleteCourseList(@RequestBody QueryVO queryVO) {
        Long[] courseIds = queryVO.getCourseIds();
        int count = courseService.deleteListByIdList(courseIds);
        if(count > 0) {
            return Result.getSuccessResult("批量删除课程信息成功");
        } else {
            return Result.getSuccessResult("批量删除课程信息失败");
        }
    }

    /**
     * 根据课程ID来增加课程学习次数
     * @param courseId 课程的id
     * @return
     */
    @Override
    public Result addCourseReadNum(@PathVariable Long courseId) {
        int count = courseService.addCourseReadNum(courseId);
        if(count > 0) {
            return Result.getSuccessResult("批量删除课程信息成功");
        } else {
            return Result.getSuccessResult("批量删除课程信息失败");
        }
    }
}
