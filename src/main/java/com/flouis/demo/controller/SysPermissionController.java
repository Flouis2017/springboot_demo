package com.flouis.demo.controller;

import com.flouis.demo.base.result.JsonResult;
import com.flouis.demo.entity.SysPermission;
import com.flouis.demo.entity.SysRolePermission;
import com.flouis.demo.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	/**
	 * @description 查询所有权限
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult queryAll(){
		return JsonResult.success(this.sysPermissionService.queryAll());
	}

	/**
	 * @description 跳转至添加/编辑页
	 */
	@RequestMapping("/edit")
	public String edit(Model model, Long id){
		SysPermission sysPermission;
		if (id == null){
			sysPermission = new SysPermission();
		} else {
			sysPermission = this.sysPermissionService.queryById(id);
		}
		model.addAttribute("editObj", sysPermission);
		return "sys-permission/edit";
	}

}
