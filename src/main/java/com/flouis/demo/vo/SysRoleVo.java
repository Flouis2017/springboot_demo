package com.flouis.demo.vo;

import com.flouis.demo.util.StringUtil;

public class SysRoleVo extends SearchVo {

	private Long id;
	private String name;
	private String description;
	private String permissionIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return StringUtil.getString(this.description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPermissionIds() {
		return StringUtil.getString(this.permissionIds);
	}

	public void setPermissionIds(String permissionIds) {
		this.permissionIds = permissionIds;
	}

	public String getName() {
		return StringUtil.getString(this.name);
	}

	public void setName(String name) {
		this.name = name;
	}
}
