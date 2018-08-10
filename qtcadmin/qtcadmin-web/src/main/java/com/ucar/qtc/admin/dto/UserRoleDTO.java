package com.ucar.qtc.admin.dto;

/**
 * @author: cong.zhou01
 * @description: 用户角色DTO
 * @date: 2018/8/7 10:19
 */
public class UserRoleDTO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private Long id;
    private String name;
    private boolean checked;

}
