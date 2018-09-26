package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.BannerDO;
import com.ucar.qtc.admin.domain.RecommandCourseDO;
import com.ucar.qtc.admin.domain.UserDO;
import com.ucar.qtc.admin.dto.UserDTO;
import com.ucar.qtc.admin.dto.do2dto.UserConvert;
import com.ucar.qtc.admin.rpc.CourseServiceRpc;
import com.ucar.qtc.admin.service.BannerService;
import com.ucar.qtc.admin.service.RecCourseService;
import com.ucar.qtc.admin.service.UserService;
import com.ucar.qtc.admin.vo.CourseVO;
import com.ucar.qtc.admin.vo.QueryVO;
import com.ucar.qtc.common.utils.PageUtils;
import com.ucar.qtc.common.utils.Query;
import com.ucar.qtc.common.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Api(description = "前台页面API接口")
@RestController
@RequestMapping("/pages")
public class FrontPageController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private RecCourseService recCourseService;

    @Autowired
    private CourseServiceRpc courseService;

    @Autowired
    private UserService userService;

    @ApiOperation(value="获取推荐图片", notes="获取推荐图片")
    @GetMapping(value="getRecBanner", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult bannerList() {
        int limit = 6;
        int offset = 0;
        int page = 1;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("limit",limit);
        params.put("offset",offset);
        params.put("page",page);
        Query query = new Query(params);
        List<BannerDO> bannerList = bannerService.list(query);
        int total = bannerService.count(query);
        PageUtils pageUtils = new PageUtils(bannerList, total);
        return ResponseResult.page(pageUtils);
    }

    @ApiOperation(value="获取推荐课程", notes="获取推荐课程")
    @GetMapping(value="getRecCourse", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult getRecourseList(){

        //得到所有推荐课程信息
        int pageSize = 8;
        List<RecommandCourseDO> recommandCourseList = recCourseService.list(null);
        if(recommandCourseList.size() == 0){
            return ResponseResult.error(-1,"无推荐课程信息");
        }
        //得到推荐课程id数组
        Long[] listIds = new Long[recommandCourseList.size()];
        Iterator iterator = recommandCourseList.iterator();
        int index = 0;
        while(iterator.hasNext()){
            listIds[index++] = ((RecommandCourseDO)iterator.next()).getCourseId();
        }
        QueryVO queryVO = new QueryVO();
        queryVO.setCourseIds(listIds);
        queryVO.setIsInValidDate(true);
        Map<String,Object> ids = new HashMap<String,Object>();
        ids.put("id",listIds);
        List<CourseVO> listCourseVo = courseService.getRecCourseList(queryVO).getRe();
        List<CourseVO> list = new ArrayList<CourseVO>();
        for(int i = 0;i <listIds.length; i++){
            iterator = listCourseVo.iterator();
            while(iterator.hasNext()){
                CourseVO tempCourseVO = (CourseVO)iterator.next();
                if(tempCourseVO.getCourseId().equals(listIds[i])) {
                    list.add(tempCourseVO);
                    break;
                }
            }
            if(list.size() == 8) {
                break;
            }
        }
        return ResponseResult.ok().put("list",list);
    }

    @ApiOperation(value="根据ID获取用户信息", notes="根据ID获取用户信息")
    @GetMapping(value="getUserInfoById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult getUserInfoById(@RequestParam("id") Long id) {
        UserDTO userDTO = UserConvert.MAPPER.do2dto(userService.get(id));
        return ResponseResult.ok().put("data",userDTO);
    }

    @ApiOperation(value="根据IDS批量获取用户信息", notes="根据IDS批量获取用户信息")
    @GetMapping(value="getUsersInfoById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult getUsersInfoById(@RequestParam("ids") Long[] ids) {
        List<UserDTO> userDTOS = new LinkedList<UserDTO>();
        for (UserDO userDO:userService.getUsers(ids)){
            userDTOS.add(UserConvert.MAPPER.do2dto(userDO));
        }
        return ResponseResult.ok().put("data",userDTOS);
    }

    @ApiOperation(value="获取ID获取学员信息", notes="获取ID获取学员信息")
    @GetMapping(value="getStudentInfoById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult getStudentInfoById(@RequestParam(value="id",defaultValue="0") Long id,@RequestParam(value="id",defaultValue="-1")int type) {
        List<UserDTO> userDTOS = new LinkedList<UserDTO>();
        int relationType = (type == 0) ? null : type;
        for(UserDO userDO:userService.getStudentById(id,type)){
            userDTOS.add(UserConvert.MAPPER.do2dto(userDO));
            System.out.println("执行成功");
        }
        return ResponseResult.ok().put("data",userDTOS);
    }

    @ApiOperation(value="获取ID获取老师信息", notes="获取ID获取老师信息")
    @GetMapping(value="getTeacherInfoById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult getTeacherInfoById(@RequestParam("id") Long id,@RequestParam(value="id",defaultValue="0")int type) {
        List<UserDTO> userDTOS = new LinkedList<UserDTO>();
        int relationType = (type == 0) ? null : type;
        for(UserDO userDO:userService.getTeacherById(id,type)){
            userDTOS.add(UserConvert.MAPPER.do2dto(userDO));
        }
        return ResponseResult.ok().put("data",userDTOS);
    }

    @ApiOperation(value="修改用户信息", notes="修改用户信息")
    @PostMapping(value="updateUserInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult updateUserInfo(@RequestBody UserDO userDO){
        if(userDO == null)
            return ResponseResult.error("更新信息为空");
        UserDO user = new UserDO();
        user.setUserId(userDO.getUserId());
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        if(userService.update(user)>0){
         return ResponseResult.ok();
        }
        return ResponseResult.error();
    }
}
