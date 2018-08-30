package com.ucar.qtc.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * guodong.zhang
 */
public class BannerDO implements Serializable {
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
    // 删除标记 0删除 1不删除
    private Integer delFlag;

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


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getBannerDescription() {
        return bannerDescription;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
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

    @Override
    public String toString() {
        return "BannerDO{" +
                "id=" + id +
                ", bannerTitle='" + bannerTitle + '\'' +
                ", bannerDescription='" + bannerDescription + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", orderNum=" + orderNum +
                ", bannerImg='" + bannerImg + '\'' +
                ", publishTime=" + publishTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}

