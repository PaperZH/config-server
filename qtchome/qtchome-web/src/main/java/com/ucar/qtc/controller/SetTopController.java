package com.ucar.qtc.controller;
import com.ucar.qtc.service.SetTopService;
import com.ucar.qtc.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : pingli.zheng
 * @Description : 页面推荐内容
 * @Date : 8:22 2018/8/16
 */
@RestController
@RequestMapping("/setTop")
public class SetTopController {
    @Autowired
//    private SetTopService setTopService;
    /**
     * 首页轮播图
     */
      @RequestMapping(value = "/getBanner",method = RequestMethod.GET)
      public ResponseResult getBanner(){
          System.out.println("......");
        ArrayList<Map> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("id",Integer.toString(i));
            hashMap.put("src","static/image/1.jpg");
            list.add(hashMap);
        }
        return ResponseResult.data(list);
      }

    /**
     * 首页课程推荐
     */
    @RequestMapping(value = "/topCourse",method = RequestMethod.GET)
    public String getTopCourse(){
        return "";
    }
    /**
     * 首页课程计划推送
     */
    @RequestMapping(value = "/topPlan",method = RequestMethod.GET)
    public  String getTopPlan(){
        return "";
    }
}
