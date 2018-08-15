package com.ucar.qtc.common.dto;

/**
 * @author: cong.zhou01
 * @description: 公共登陆用户DTO
 * @date: 2018/8/7 10:19
 */
public class LoginUserDTO {
    /**
     * 用户id
     */
    private String id;
    /**
     * 用户登录名
     */
    private String username;
    /**
     * 用户真实姓名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 图片
     */
    private String avatar;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
