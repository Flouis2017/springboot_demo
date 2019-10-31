package com.flouis.demo.vo;

import com.flouis.demo.util.StringUtil;
import lombok.ToString;

@ToString
public class SysUserVo extends SearchVo{

	private String username;
	private Integer gender;
	private Long roleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return StringUtil.getString(this.username);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
}
