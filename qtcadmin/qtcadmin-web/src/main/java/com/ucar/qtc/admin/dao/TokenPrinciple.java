package com.ucar.qtc.admin.dao;

import java.security.Principal;

/**
 * @author: cong.zhou01
 * @description: token实体
 * @date: 2018/8/7 10:19
 */
public class TokenPrinciple implements Principal {
    private String name;

    public TokenPrinciple(String name) {
        setName(name);
    }

    @Override
    public String getName() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TokenPrinciple{" +
                "name='" + name + '\'' +
                '}';
    }
}
