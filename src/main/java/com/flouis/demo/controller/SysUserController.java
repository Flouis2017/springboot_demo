package com.flouis.demo.controller;

import com.flouis.demo.base.result.TableResult;
import com.flouis.demo.entity.SysRole;
import com.flouis.demo.entity.SysUser;
import com.flouis.demo.service.SysRoleService;
import com.flouis.demo.service.SysUserService;
import com.flouis.demo.vo.SysUserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * @description 列表页
	 */
	@RequestMapping("/list")
	public String list(){
		return "sys-user/list";
	}

	/**
	 * @description 列表数据-分页查询
	 */
	@RequestMapping("/list.json")
	@ResponseBody
	public TableResult list(SysUserVo vo){
		log.info("request param: " + vo.toString());
		Page page = PageHelper.startPage(vo.getPage(), vo.getLimit());
		this.sysUserService.queryList(vo);
		return TableResult.success(page.getTotal(), page.getResult());
	}

	/**
	 * @description 编辑页-添加/编辑弹窗
	 */
	@RequestMapping("/edit")
	public String edit(Model model, SysUser editObj){
		List<SysRole> roleList = this.sysRoleService.queryAll();
		model.addAttribute("roleList", roleList);
		model.addAttribute("editObj", editObj);
		return "sys-user/edit";
	}

}
