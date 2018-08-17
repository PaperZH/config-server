package com.ucar.qtc.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author : pingli.zheng
 * @Description : 页面推荐内容
 * @Date : 8:22 2018/8/16
 */
@RestController
@RequestMapping("/setTop")
public class SetTopController {

    /**
     * 首页图片推送
     */
      @RequestMapping(value = "/topImg",method = RequestMethod.GET)
      public String  getTopImg(){
          return "";
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
