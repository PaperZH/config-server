package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.service.HCoursewareService;
import com.ucar.qtcassist.api.model.BaseCoursewareListDTO;
import com.ucar.qtcassist.api.model.CoursewareTypeDTO;
import com.ucar.qtcassist.api.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月30日
 */
@RestController
@RequestMapping("/courseware")
public class HCoursewareController {

    @Autowired
    HCoursewareService hcoursewareService;

    @RequestMapping(value = "/frontPage/getAllBaseCoursewares")
    public Result<List<BaseCoursewareListDTO>> getAllBaseCoursewares(){
        return hcoursewareService.getAllBaseCoursewares();
    }

    @RequestMapping(value = "/frontPage/getAllTypes")
    public Result<List<CoursewareTypeDTO>> getAllTypes(){
        return hcoursewareService.getAllType();
    }

    @RequestMapping(value = "/upLoad",method = RequestMethod.POST)
    public Result upLoad( MultipartFile file) throws Exception{
        return hcoursewareService.uploadCourseware(file);
    }

    @RequestMapping(value = "/downLoadCourseware" ,method = RequestMethod.POST)
    public void downLoadCourseware(HttpServletResponse response, @RequestParam Long baseCoursewareId){
        Result<File> res = hcoursewareService.downLoadCourseware(baseCoursewareId);
        File temFile =  res.getRe();
        try {
            InputStream is = new FileInputStream(temFile);
            response.reset();
            response.setContentType("bin");
            response.addHeader("Content-Disposition", "attachment; id="+baseCoursewareId);
            byte[] b = new byte[100];
            int len;
            while ((len = is.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
