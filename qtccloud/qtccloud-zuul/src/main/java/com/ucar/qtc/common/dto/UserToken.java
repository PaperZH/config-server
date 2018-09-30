package com.ucar.qtc.common.dto;

import java.io.Serializable;

/**
 * @author: cong.zhou01
 * @description: 用户token登陆DTO
 * @date: 2018/8/7 10:19
 */
public class UserToken implements Serializable{
    private static final long serialVersionUID = 1L;

    public UserToken() {
    }

    public UserToken(String username, String userId) {
        this.userId = userId;
        this.username = username;
    }

    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户登录名
     */
    private String username;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
