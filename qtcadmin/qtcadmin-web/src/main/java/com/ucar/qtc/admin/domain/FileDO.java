package com.ucar.qtc.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cong.zhou01
 * @name FileDO
 * @description TODO
 * @date 2018-08-16 10:04
 */
public class FileDO implements Serializable {
    private static final long serialVersionUID = 1L;
    // 主键
    private Long id;
    //文件类型
    private Integer type;
    //URL地址
    private String url;
    // 标题
    private String title;
    // 描述信息
    private String content;
    // 排序
    private Integer orderNum;
    //创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 更新时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    // 删除标记 0删除 1不删除
    private Integer delFlag;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：文件类型
     */
    public void setType(Integer type) {
        this.type = type;
    }
    /**
     * 获取：文件类型
     */
    public Integer getType() {
        return type;
    }
    /**
     * 设置：URL地址
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * 获取：URL地址
     */
    public String getUrl() {
        return url;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}
