package com.ucar.qtcassist.courseware.controller;

import com.github.pagehelper.PageInfo;
import com.ucar.qtcassist.api.CoursewareApi;
import com.ucar.qtcassist.api.model.*;
import com.ucar.qtcassist.api.model.VO.AddCoursewareQueryVO;
import com.ucar.qtcassist.api.model.VO.CoursewareQueryVO;
import com.ucar.qtcassist.courseware.model.DO.BaseCoursewareDO;
import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import com.ucar.qtcassist.courseware.model.DO.CoursewareTypeDO;
import com.ucar.qtcassist.courseware.model.DTO.FileDTO;
import com.ucar.qtcassist.courseware.service.*;
import com.ucar.qtcassist.courseware.util.FileDownLoad;
import com.ucar.qtcassist.courseware.util.MesListener;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月13日
 */
@RestController
@RequestMapping("/courseware")
public class CoursewareController implements CoursewareApi {
    private static Logger LOGGER = LoggerFactory.getLogger(CoursewareController.class);
    @Autowired
    private BaseCoursewareService baseCoursewareService;
    @Autowired
    private CoursewareService coursewareService;
    @Autowired
    private RemoteFileService remoteFileService;
    @Autowired
    private MqService mqService;
    @Autowired
    private CoursewareTypeService coursewareTypeService;
    @Autowired
    private FileService fileService;
    @Autowired
    MesListener mesListener;
    @Autowired
    FileDownLoad fileDownLoad;


    /**
     * test
     */
    @RequestMapping(value = "/addCoursewareFromLocal", method = RequestMethod.POST)
    //Result addCoursewareFromSys(@RequestBody MultipartFile file,@RequestBody UploadCoursewareDTO uploadCoursewareDTO,@RequestBody HttpServletRequest request) throws Exception;

    public Result addCoursewareFromSys(@RequestParam("file") MultipartFile file, @RequestParam("uploadCoursewareDTO") UploadCoursewareDTO uploadCoursewareDTO) throws Exception {
        return Result.getSuccessResult("MultipartFile test");
    }

    /**
     * 系统库课件列表
     *
     * @param
     * @return
     */
    @Override
    @RequestMapping(value = "/frontPage/getAllBaseCoursewares", method = RequestMethod.POST)
    public Result<List<BaseCoursewareListDTO>> getAllBaseCoursewares() {
        return Result.getSuccessResult(baseCoursewareService.getAllBaseCoursewares());
    }

    /**
     * 课件类型列表
     */
    @Override
    @RequestMapping(value = "/frontPage/getAllType", method = RequestMethod.GET)
    public Result<List<CoursewareTypeDTO>> getAllType() {
        List<CoursewareTypeDTO> coursewareTypeDTOList = new ArrayList<>();
        List<CoursewareTypeDO> list = coursewareTypeService.getAllType();
        if(list != null && list.size() > 0) {
            for(int i = 0; i < list.size(); i++) {
                CoursewareTypeDTO coursewareTypeDTO = new CoursewareTypeDTO();
                coursewareTypeDTO.setId(list.get(i).getId());
                coursewareTypeDTO.setTypeName(list.get(i).getTypeName());
                coursewareTypeDTOList.add(coursewareTypeDTO);
            }
            return Result.getSuccessResult(coursewareTypeDTOList);
        }
        return null;
    }


    /**
     * 从系统库中选择以后 插入课件表
     *
     * @param
     * @return
     */
    @Override
    @RequestMapping(value = "/addCourseware", method = RequestMethod.POST)
    public Result addCourseware(@RequestBody CourseCoursewareDTO courseCoursewareDTO) {
        CoursewareDO coursewareDO = new CoursewareDO();
        coursewareDO.setBaseCoursewareId(courseCoursewareDTO.getBaseCoursewareId());
        coursewareDO.setCoursewareDescription(courseCoursewareDTO.getDescribe());
        coursewareDO.setCoursewareName(courseCoursewareDTO.getName());
        coursewareDO.setPublishTime(new Date(System.currentTimeMillis()));
        coursewareDO.setUpdateTime(new Date(System.currentTimeMillis()));
        coursewareDO.setTypeId(courseCoursewareDTO.getTypeId());
        coursewareDO.setCoursewareNum(courseCoursewareDTO.getHour());
        coursewareService.addCourseware(coursewareDO);
        return Result.getSuccessResult(coursewareDO.getId());
    }

    /**
     * 课件上传
     */
    @Override
    @RequestMapping(value = "/uploadCourseware", method = RequestMethod.POST)
    public Result uploadCourseware(MultipartFile file) throws Exception {
        InputStream in =file.getInputStream();
        String name = file.getOriginalFilename();
        String sourceUrl = remoteFileService.uploadFile(in, name);
        BaseCoursewareDO baseCoursewareDO=new BaseCoursewareDO();
        baseCoursewareDO.setUpdateTime(new Date(System.currentTimeMillis()));
        baseCoursewareDO.setPublishTime(new Date(System.currentTimeMillis()));
        baseCoursewareDO.setSourceUrl(sourceUrl);
        baseCoursewareService.addBaseCourseware(baseCoursewareDO);
        Long newBaseid=baseCoursewareDO.getId();
        return Result.getSuccessResult(newBaseid);
    }

    /**
     * 本地课件操作
     */
    @Override
    @RequestMapping(value = "/saveCourseware", method = RequestMethod.POST)
    public Result saveCourseware(@RequestBody CourseCoursewareDTO courseCoursewareDTO) {
        InputStream is = null;
            try {
                Long baseCoursewareId = courseCoursewareDTO.getBaseCoursewareId();
                //插表   baseCourseware
                BaseCoursewareDO baseCoursewareDO=new BaseCoursewareDO();
                baseCoursewareDO.setId(baseCoursewareId);
                baseCoursewareDO.setUpdateTime(new Date(System.currentTimeMillis()));
                baseCoursewareDO.setPublishTime(new Date(System.currentTimeMillis()));
                baseCoursewareDO.setCoursewareName(courseCoursewareDTO.getName());
                baseCoursewareDO.setCoursewareDescription(courseCoursewareDTO.getDescribe());
                baseCoursewareDO.setTypeId(courseCoursewareDTO.getTypeId());
                baseCoursewareService.updateBaseCourseware(baseCoursewareDO);
                //插表   Courseware
                CoursewareDO coursewareDO=new CoursewareDO();
                coursewareDO.setCoursewareNum(courseCoursewareDTO.getHour());
                coursewareDO.setPublishTime(new Date(System.currentTimeMillis()));
                coursewareDO.setUpdateTime(new Date(System.currentTimeMillis()));
                coursewareDO.setBaseCoursewareId(baseCoursewareId);
                coursewareDO.setCoursewareDescription(courseCoursewareDTO.getDescribe());
                coursewareDO.setTypeId(courseCoursewareDTO.getTypeId());
                coursewareDO.setCoursewareName(courseCoursewareDTO.getName());
                coursewareService.addCourseware(coursewareDO);
                Long newCoursewareId =coursewareDO.getId();
                //将上传的文件投递到mq中
                FileDTO fileDTO=new FileDTO();
                fileDTO.setId(baseCoursewareId);
                mqService.msgSend(fileDTO);

                return Result.getSuccessResult(newCoursewareId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return Result.getServiceError();
    }

    /**
     * 完成本地上传后获取课件详情
     */
    @Override
    @RequestMapping(value = "/getBaseCourseware/{baseCoursewareId}", method = RequestMethod.GET)
    public Result<BaseCoursewareDTO> getBaseCourseware(@PathVariable Long baseCoursewareId) {

        return Result.getSuccessResult(baseCoursewareService.getBaseCourseware(baseCoursewareId));
    }

    @Override
    @RequestMapping(value = "/queryList")
    public Result<BackDTO> queryList(@RequestBody CoursewareQueryVO coursewareQueryVO){
       return Result.getSuccessResult(coursewareService.queryPage(coursewareQueryVO));
    }

    @Override
    @RequestMapping(value = "/getAddCoursewarePageList" ,method = RequestMethod.POST)
    public Result getAddCoursewarePageList(@RequestBody AddCoursewareQueryVO addCoursewareQueryVO){
        return null;
    }

    @Override
    @RequestMapping(value = "/downLoadCourseware" ,method = RequestMethod.GET)
    public Result<File> downLoadCourseware(Long baseCoursewareId){
        BaseCoursewareDTO baseCoursewareDTO = baseCoursewareService.getBaseCourseware(baseCoursewareId);
        return Result.getSuccessResult(fileDownLoad.downloadNet(baseCoursewareDTO.getSourceUrl()));
    }
}
