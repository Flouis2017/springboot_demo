package com.flouis.demo.vo;

import com.flouis.demo.util.StringUtil;

public class SysRoleVo extends SearchVo {

	private String name;

	public String getName() {
		return StringUtil.getString(this.name);
	}

	public void setName(String name) {
		this.name = name;
	}
}
