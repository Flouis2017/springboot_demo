package com.flouis.demo.service;

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
//		JDK8递归，返回标准数据结构的数据
//		TreeUtil.setPermissionTree(0L, allList, tree);
//		return JsonResult.success(allList);

//		返回简单数据结构的数据(列表)->转换成标准数据结构的数据交由ztree完成
		return JsonResult.success(TreeUtil.getSimplePermissionTree(allList));

//		传统递归，返回标准数据结构的数据
//		return JsonResult.success(TreeUtil.buildPermissionTree(TreeUtil.getZTreeRoot(), TreeUtil.toZTreeList(allList)));
	}

	public List<SysRolePermission> queryByRoleId(Long roleId) {
		return this.sysRolePermissionMapper.queryByRoleId(roleId);
	}

	public List<SysPermission> queryAll() {
		return this.sysPermissionMapper.queryAll();
	}

	public SysPermission queryById(Long id) {
		return this.sysPermissionMapper.selectByPrimaryKey(id);
	}
}
