package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.service.HCoursewareService;
import com.ucar.qtcassist.api.model.BaseCoursewareDTO;
import com.ucar.qtcassist.api.model.BaseCoursewareListDTO;
import com.ucar.qtcassist.api.model.CoursewareTypeDTO;
import com.ucar.qtcassist.api.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.PublicKey;
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
    private HCoursewareService hcoursewareService;

    public static final String URLPREFIX = "http://udfstest.10101111.com/ucarudfs/resource/";

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

    @RequestMapping(value = "/frontPage/downLoadCourseware" ,method = RequestMethod.GET)
    public void downLoadCourseware(HttpServletResponse response, @RequestParam String fileName,@RequestParam String sourceUrl){
        try {
            URL url = new URL(URLPREFIX+sourceUrl);
            URLConnection conn = url.openConnection();
            int point = sourceUrl.lastIndexOf(".");
            String sfix = sourceUrl.substring(point,sourceUrl.length());
            try(InputStream inStream = conn.getInputStream()){
                response.reset();
                response.setContentType("bin");
//                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename="+java.net.URLEncoder.encode(fileName, "UTF-8") + sfix);
                byte[] b = new byte[100];
                int len;
                while ((len = inStream.read(b)) > 0){
                    response.getOutputStream().write(b, 0, len);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/frontPage/scanCourseware" ,method = RequestMethod.POST)
    public Result scanCourseware(@RequestBody Long baseCoursewareId){
        BaseCoursewareDTO baseCoursewareDTO = hcoursewareService.getBaseCourseware(baseCoursewareId).getRe();
        if(baseCoursewareDTO!=null&&!baseCoursewareDTO.getPreviewUrl().equals("")&&baseCoursewareDTO.getPreviewUrl()!=null){
            return Result.getSuccessResult(URLPREFIX+baseCoursewareDTO.getPreviewUrl());
        }else {
            return Result.getServiceError();
        }


    }
}
