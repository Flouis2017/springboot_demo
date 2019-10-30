package com.flouis.demo.controller;

import com.flouis.demo.base.result.JsonResult;
import com.flouis.demo.entity.SysRolePermission;
import com.flouis.demo.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sys/permission")
public class SysPermissionController {

	@Autowired
	private SysPermissionService sysPermissionService;

	/**
	 * @description 获取权限树
	 */
	@RequestMapping("/getTree")
	@ResponseBody
	public JsonResult getTree(){
		return this.sysPermissionService.getTree();
	}

	/**
	 * @description 通过角色id获取角色权限列表--用于角色编辑权限树回显
	 */
	@RequestMapping("/getIdListByRoleId")
	@ResponseBody
	public JsonResult getIdListByRoleId(Long roleId){
		List<SysRolePermission> list = this.sysPermissionService.queryByRoleId(roleId);
		return JsonResult.success(list);
	}

}
