package com.flouis.demo.controller;

import com.flouis.demo.base.result.JsonResult;
import com.flouis.demo.base.result.TableResult;
import com.flouis.demo.entity.SysUser;
import com.flouis.demo.service.SysUserService;
import com.flouis.demo.vo.SysUserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;

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
		Page page = PageHelper.startPage(vo.getPage(), vo.getLimit());
		this.sysUserService.queryList(vo);
		return TableResult.success(page.getTotal(), page.getResult());
	}

	@RequestMapping("/edit")
	public String edit(){
		return "sys-user/edit";
	}

	@RequestMapping("/get")
	@ResponseBody
	public JsonResult getById(Long id){
		SysUser sysUser = this.sysUserService.getById(id);
		return  JsonResult.success(sysUser);
	}

}
