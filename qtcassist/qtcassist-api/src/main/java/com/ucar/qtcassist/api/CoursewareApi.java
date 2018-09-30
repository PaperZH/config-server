package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.model.*;
import com.ucar.qtcassist.api.model.VO.AddCoursewareQueryVO;
import com.ucar.qtcassist.api.model.VO.CoursewareQueryVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Description: TODO
 *
 * @author
 * @since
 */
public interface CoursewareApi {

    @RequestMapping(value = "/getAllBaseCoursewares", method = RequestMethod.POST)
    Result<List<BaseCoursewareListDTO>> getAllBaseCoursewares();

    @RequestMapping(value = "/uploadCourseware", method = RequestMethod.POST)
    Result uploadCourseware(MultipartFile file) throws Exception;

    @RequestMapping(value = "/frontPage/getBaseCourseware", method = RequestMethod.POST)
    public Result<BaseCoursewareDTO> getBaseCourseware(@RequestParam Long baseCoursewareId);

    @RequestMapping(value = "/getAllType",method = RequestMethod.GET)
    Result<List<CoursewareTypeDTO>> getAllType();

    /**
     * 从系统库中选择以后 插入课件表
     * @param
     * @return
     */
    @RequestMapping(value = "/addCourseware", method = RequestMethod.POST)
    Result addCourseware(@RequestBody CourseCoursewareDTO courseCoursewareDTO);

    @RequestMapping(value = "/saveCourseware", method = RequestMethod.POST)
    Result saveCourseware(@RequestBody CourseCoursewareDTO courseCoursewareDTO);

    @RequestMapping(value = "/queryList" )
    Result<BackDTO> queryList(@RequestBody CoursewareQueryVO coursewareQueryVO);

    @RequestMapping("/getAddCoursewarePageList")
    Result getAddCoursewarePageList(@RequestBody AddCoursewareQueryVO addCoursewareQueryVO);

    /**
     * 根据baseCoursewareId 下载对应的文件
     * @param baseCoursewareId
     * @return
     */
    @RequestMapping(value = "/downLoadCourseware" ,method = RequestMethod.GET)
    Result<File> downLoadCourseware(Long baseCoursewareId);
}