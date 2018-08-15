package com.ucar.qtc.common.dto;

import java.io.Serializable;

/**
 * @author: cong.zhou01
 * @description: 用户token登陆DTO
 * @date: 2018/8/7 10:19
 */
public class UserToken implements Serializable{
    private static final long serialVersionUID = 1L;

    public UserToken(String username, String userId, String name, String nickname) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.nickname = nickname;
    }

    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户登录名
     */
    private String username;
    /**
     * 用户真实姓名
     */
    private String name;

    private String nickname;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
