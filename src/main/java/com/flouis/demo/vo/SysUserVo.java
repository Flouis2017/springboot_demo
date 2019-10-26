package com.flouis.demo.vo;

import com.flouis.demo.util.StringUtil;

public class SysUserVo extends SearchVo{

	private String username;
	private Integer age;
	private Integer gender;

	public String getUsername() {
		return StringUtil.getString(this.username);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
}
