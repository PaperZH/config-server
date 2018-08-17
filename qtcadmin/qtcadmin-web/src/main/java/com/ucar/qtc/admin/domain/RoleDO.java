package com.ucar.qtc.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author: cong.zhou01
 * @description: 角色管理DO
 * @date: 2018/8/7 10:19
 */
public class RoleDO {
	private Long roleId;
	private String roleName;
	private String roleDesc;
	private String remark;
	private Integer delFlag;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	private List<Long> menuIds;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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

	public List<Long> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Long> menuIds) {
		this.menuIds = menuIds;
	}

	@Override
	public String toString() {
		return "RoleDO{" +
				"roleId=" + roleId +
				", roleName='" + roleName + '\'' +
				", roleDesc='" + roleDesc + '\'' +
				", remark='" + remark + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", delFlag=" + delFlag +
				", menuIds=" + menuIds +
				'}';
	}
}
