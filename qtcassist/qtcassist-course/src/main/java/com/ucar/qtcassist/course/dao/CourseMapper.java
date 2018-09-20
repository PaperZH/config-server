package com.ucar.qtcassist.course.dao;

import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.DO.QueryDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface CourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseDO record);

    int insertSelective(CourseDO record);

    CourseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourseDO record);

    int updateByPrimaryKey(CourseDO record);

    /**
     * 获取所有课程的id、courseName、courseDescription
     * @param queryDO (String courseName, Integer startIndex, Integer pageSize)
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Integer startIndex 分页查询的起始索引
     * Integer pageSize 分布查询的每页的记录数目
     * @return
     */
    List<CourseDO> getCourseIdAndCourseName(QueryDO queryDO);

    /**
     * 统计指定id集合的课程中符合指定条件的课程的数量
     * @param ids 要考虑的课程集合的id
     * @param queryDO (courseName, startDate, endDate)
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有的课程）
     * Date startDate 课程发布的起始时间（可以为null，表示不考虑课程的发布时间）
     * Date endDate 课程发布的截止时间（可以为null，表示不考虑课程的发布时间）
     * @return
     */
    Integer getTotalByIdListAndCondition(@Param("ids") List<Long> ids, @Param("condition") QueryDO queryDO);

    /**
     * 查询课程页课程列表
     * @param queryDO(courseName, startIndex, pageSize, orderType)
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Integer startIndex 分页查询的起始索引号
     * Integer pageSize 分布查询的每页的记录数目
     * String orderType 查询的排序类型，default（默认）, time(发布时间降序), hot(点赞数量降序)
     * @return
     */
    List<CourseDO> getList(QueryDO queryDO);

    /**
     * 根据多条件查询用户收藏课程和发布的课程列表
     * @param queryDO(courseIds, courseName, startDate, endDate, startIndex, pageSize)
     * Long[] courseIds 要匹配的所有课程的id数组
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有的课程）
     * Date startDate 课程发布的起始时间（可以为null，表示不考虑课程的发布时间）
     * Date endDate 课程发布的截止时间（可以为null，表示不考虑课程的发布时间）
     * Integer startIndex 分页查询的起始索引号
     * Integer pageSize 分布查询的每页的记录数目
     * @return
     */
    List<CourseDO> getListByCondition(QueryDO queryDO);

    /**
     * 查询所有的课程的id，invalid_data, del_flag
     * @param queryDO
     * @return
     */
    List<CourseDO> getAllCourseIds(QueryDO queryDO);

    /**
     * 根据课程id批量删除课程
     * @param ids
     * @return
     */
    Integer deleteListByIdList(@Param("ids") Long[] ids);

    /**
     * 根据id更新某一门课程的点赞数
     * @param courseId 课程的id
     * @param change 点赞数的变化值，（+1：点赞， -1：取消点赞）
     * @return
     */
    Integer updatePraiseNum(@Param("courseId") Long courseId, @Param("change") int change);

    /**
     * 根据课程ID来增加课程学习次数
     * @param courseId 课程的id
     * @return
     */
    Integer addCourseReadNum(@Param("courseId") Long courseId);

}