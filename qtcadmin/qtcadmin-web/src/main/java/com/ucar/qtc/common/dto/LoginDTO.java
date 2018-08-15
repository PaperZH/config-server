package com.ucar.qtc.common.dto;

import javax.validation.constraints.NotNull;

/**
 * @author: cong.zhou01
 * @description: 公共登陆DTO
 * @date: 2018/8/7 10:19
 */
public class LoginDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
