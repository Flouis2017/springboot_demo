package com.flouis.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.flouis.demo.base.result.JsonResult;
import com.flouis.demo.entity.SysPermission;
import com.flouis.demo.entity.SysRolePermission;
import com.flouis.demo.mapper.SysPermissionMapper;
import com.flouis.demo.mapper.SysRolePermissionMapper;
import com.flouis.demo.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionService {

	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;

	public JsonResult getTree() {
		List<SysPermission> allList = this.sysPermissionMapper.queryAll();
		JSONArray tree = new JSONArray();
		TreeUtil.setPermissionTree(0L, allList, tree);
		return JsonResult.success(tree);
	}

	public List<SysRolePermission> queryByRoleId(Long roleId) {
		return this.sysRolePermissionMapper.queryByRoleId(roleId);
	}
}
