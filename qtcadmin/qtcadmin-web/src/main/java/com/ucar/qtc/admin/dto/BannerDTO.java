package com.ucar.qtc.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述:
 *
 * @param: $param$
 * @return: $return$
 * @auther: $user$
 * @date: $date$ $time$
 */
public class BannerDTO implements Serializable {
        private static final long serialVersionUID = 1L;
        // 主键
        private Long id;
        //banner标题
        private String bannerTitle;
        //banner描述
        private String bannerDescription;
        //链接URL地址
        private String bannerUrl;
        // 排序
        private Integer orderNum;
        // imgURL地址
        private String bannerImg;
        //创建时间
        @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
        private Date publishTime;
        // 更新时间
        @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
        private Date updateTime;

        public Date getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(Date publishTime) {
            this.publishTime = publishTime;
        }


        public static long getSerialVersionUID() {
            return serialVersionUID;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getBannerTitle() {
            return bannerTitle;
        }

        public void setBannerTitle(String bannerTitle) {
            this.bannerTitle = bannerTitle;
        }

        public String getBannerUrl() {
            return bannerUrl;
        }

        public void setBannerUrl(String bannerUrl) {
            this.bannerUrl = bannerUrl;
        }

        public String getBannerImg() {
            return bannerImg;
        }

        public void setBannerImg(String bannerImg) {
            this.bannerImg = bannerImg;
        }

        public Date getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }

        public String getBannerDescription() {
            return bannerDescription;
        }

        public void setBannerDescription(String bannerDescription) {
            this.bannerDescription = bannerDescription;
        }

        public Integer getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(Integer orderNum) {
            this.orderNum = orderNum;
        }
}
