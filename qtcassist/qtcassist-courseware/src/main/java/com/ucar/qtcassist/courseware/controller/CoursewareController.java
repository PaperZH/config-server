package com.ucar.qtcassist.courseware.controller;

import com.ucar.qtcassist.api.CoursewareApi;
import com.ucar.qtcassist.api.model.*;
import com.ucar.qtcassist.api.model.DO.CourseTypeDO;
import com.ucar.qtcassist.courseware.dao.CoursewareTypeMapper;
import com.ucar.qtcassist.courseware.model.DO.BaseCoursewareDO;
import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import com.ucar.qtcassist.courseware.model.DO.CoursewareTypeDO;
import com.ucar.qtcassist.courseware.model.DTO.FileDTO;
import com.ucar.qtcassist.courseware.service.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
@RequestMapping("/course")
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

    /**
     *
     * test
     * */
    @RequestMapping(value = "/addCoursewareFromLocal", method = RequestMethod.POST)
    //Result addCoursewareFromSys(@RequestBody MultipartFile file,@RequestBody UploadCoursewareDTO uploadCoursewareDTO,@RequestBody HttpServletRequest request) throws Exception;

    public Result addCoursewareFromSys(@RequestParam("file") MultipartFile file,@RequestParam("uploadCoursewareDTO") UploadCoursewareDTO uploadCoursewareDTO) throws Exception{
        return Result.getSuccessResult("MultipartFile test");
    }

    /**
     * 系统库课件列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getAllBaseCoursewares", method = RequestMethod.POST)
    public Result<List<BaseCoursewareListDTO>> getAllBaseCoursewares() {
        return Result.getSuccessResult(baseCoursewareService.getAllBaseCoursewares());
    }

    /**
     * 课件类型列表
     *
     * */
    @RequestMapping(value = "/getAllType",method = RequestMethod.GET)
    public Result<List<CoursewareTypeDTO>> getAllType(){
        List<CoursewareTypeDTO> coursewareTypeDTOList =new ArrayList<>();
        List<CoursewareTypeDO> list =coursewareTypeService.getAllType();
        if(list!=null&&list.size()>0){
            for(int i=0;i<list.size();i++){
                CoursewareTypeDTO coursewareTypeDTO =new CoursewareTypeDTO();
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
    @RequestMapping(value = "/addCourseware", method = RequestMethod.POST)
    public Result addCourseware(@RequestParam Long id) {
        CoursewareDO coursewareDO = new CoursewareDO();
        BaseCoursewareDTO baseCoursewareDTO = baseCoursewareService.getBaseCourseware(id);
        coursewareDO.setBaseCoursewareId(id);
        coursewareDO.setCoursewareDescription(baseCoursewareDTO.getCoursewareDescription());
        coursewareDO.setCoursewareName(baseCoursewareDTO.getCoursewareName());
        coursewareDO.setPublishTime(baseCoursewareDTO.getPublishTime());
        coursewareDO.setUpdateTime(baseCoursewareDTO.getUpdateTime());
        coursewareDO.setTypeId(baseCoursewareDTO.getTypeId());
        return Result.getSuccessResult(coursewareService.addCourseware(coursewareDO));
    }

    /**
     *课件上传
     *
     * */
    @RequestMapping(value = "/uploadCourseware", method = RequestMethod.POST)
    public Result uploadCourseware(MultipartFile file) throws Exception {
        String res=null;
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        if(!file.isEmpty()) {
            String coursewareName = file.getOriginalFilename();
            res = remoteFileService.saveTemFile(coursewareName);
            File file1 = new File(date+"/"+res);
            System.out.println(file1.getAbsolutePath());
            FileUtils.copyInputStreamToFile(file.getInputStream(), file1);
        }
        return Result.getSuccessResult(date+"/"+res);
    }

    /**
     * 从本地上传课件进行添加
     *
     * @param file,uploadCoursewareDTO
     */
    /*@RequestMapping(value = "/addCoursewareFromLocal", method = RequestMethod.POST)
    public Result<MqBackCoursewareDTO> addCoursewareFromSys(MultipartFile file, UploadCoursewareDTO uploadCoursewareDTO, HttpServletRequest request) throws Exception {
        FileDTO fileDTO = new FileDTO();
        BaseCoursewareDO baseCoursewareDO = new BaseCoursewareDO();
        CoursewareDO coursewareDO = new CoursewareDO();
        MqBackCoursewareDTO mqBackCoursewareDTO = new MqBackCoursewareDTO();
        if(!file.isEmpty()) {
            String coursewareName = file.getOriginalFilename();
            String sourceUrl = remoteFileService.uploadFile(file.getInputStream(), coursewareName);

            baseCoursewareDO.setCoursewareName(uploadCoursewareDTO.getCoursewareName());
            baseCoursewareDO.setCoursewareDescription(uploadCoursewareDTO.getCoursewareDescription());
            baseCoursewareDO.setPublishTime(uploadCoursewareDTO.getPublishTime());
            baseCoursewareDO.setSourceUrl(sourceUrl);
            baseCoursewareDO.setTypeId(uploadCoursewareDTO.getTypeId());

            Long baseCourseId = null;
            if(baseCoursewareService.addBaseCourseware(baseCoursewareDO) == 1) {
                baseCourseId = baseCoursewareService.getNewId();
            }

            coursewareDO.setPublishTime(uploadCoursewareDTO.getPublishTime());
            coursewareDO.setCoursewareName(uploadCoursewareDTO.getCoursewareName());
            coursewareDO.setCoursewareDescription(uploadCoursewareDTO.getCoursewareDescription());
            coursewareDO.setBaseCoursewareId(baseCourseId);
            coursewareDO.setTypeId(uploadCoursewareDTO.getTypeId());
            coursewareDO.setUpdateTime(uploadCoursewareDTO.getPublishTime());

            if(baseCourseId != null) {
                coursewareService.addCourseware(coursewareDO);
            }

            mqBackCoursewareDTO.setSourceUrl(sourceUrl);
            mqBackCoursewareDTO.setPublishTime(uploadCoursewareDTO.getPublishTime());
            mqBackCoursewareDTO.setCoursewareDescription(uploadCoursewareDTO.getCoursewareDescription());
            mqBackCoursewareDTO.setCoursewareName(uploadCoursewareDTO.getCoursewareName());

            String filePath = request.getSession().getServletContext().getRealPath("/upload/") + "";
            LOGGER.info("fileLocation:" + filePath);
            FileConstant dir = new FileConstant(filePath);
            if(!dir.exists()) {
                dir.mkdir();
            }
            String path = filePath + file.getOriginalFilename();
            FileConstant tempFile = null;
            //save to the /upload path
            try {
                tempFile = new FileConstant(path);
                //copy MultipartFile into FileConstant
                FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
                fileDTO.setFile(tempFile);
                fileDTO.setId(baseCourseId);
                fileDTO.setOriginalFilename(coursewareName);
                fileDTO.setLocation(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mqService.msgSend(fileDTO);
        }
        return Result.getSuccessResult(mqBackCoursewareDTO);
    }*/

    /**
     * 完成本地上传后获取课件详情
     */
    @RequestMapping(value = "/getBaseCourseware/{baseCoursewareId}", method = RequestMethod.GET)
    public Result<BaseCoursewareDTO> getBaseCourseware(@PathVariable Long baseCoursewareId) {

        return Result.getSuccessResult(baseCoursewareService.getBaseCourseware(baseCoursewareId));
    }
}



















/*//将MultipartFile转换为File
        FileConstant f = null;
        if(file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            try {
                ins = file.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            f = new FileConstant(file.getOriginalFilename());
            DocumentConverUtil.inputStreamToFile(ins, f);
            //FileCopyUtils.copy(this.part.getInputStream(), new FileOutputStream(dest));
        }



        FileConstant del = new FileConstant(f.toURI());
        del.delete();*/

        /*DocumentConverUtil documentConverUtil =new DocumentConverUtil();
        try {
            InputStream inputStream=file.getInputStream();
            remoteFileService.uploadFile(inputStream,coursewareName);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
