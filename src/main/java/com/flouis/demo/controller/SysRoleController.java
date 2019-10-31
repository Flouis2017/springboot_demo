package com.flouis.demo.controller;

import com.flouis.demo.base.result.JsonResult;
import com.flouis.demo.base.result.TableResult;
import com.flouis.demo.entity.SysRole;
import com.flouis.demo.service.SysRoleService;
import com.flouis.demo.vo.SysRoleVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * @description 角色列表页跳转
	 */
	@RequestMapping("/list")
	public String list(){
		return "sys-role/list";
	}

	/**
	 * @description 列表数据-分页查询
	 */
	@RequestMapping("/list.json")
	@ResponseBody
	public TableResult list(SysRoleVo vo){
		Page page = PageHelper.startPage(vo.getPage(), vo.getLimit());
		this.sysRoleService.queryList(vo);
		return TableResult.success(page.getTotal(), page.getResult());
	}

	/**
	 * @description 角色编辑页标砖
	 */
	@RequestMapping("/edit")
	public String edit(Model model, Long id){
		SysRole sysRole;
		if (id == null){
			sysRole = new SysRole();
		} else {
			sysRole = this.sysRoleService.queryById(id);
		}
		model.addAttribute("editObj", sysRole);
		return "sys-role/edit";
	}

	/**
	 * @description 角色保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(@RequestBody SysRoleVo vo){
		return this.sysRoleService.save(vo);
	}

	/**
	 * @description 角色删除(物理删除)
	 */
	@RequestMapping("/del")
	@ResponseBody
	public JsonResult del(Long id){
		return this.sysRoleService.del(id);
	}

}
