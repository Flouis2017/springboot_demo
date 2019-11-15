package com.flouis.demo.controller;

import com.flouis.demo.base.result.JsonResult;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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
	 * @description 列表数据-分页查询
	 */
	@RequestMapping("/list.json")
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:user:query')")
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
	@PreAuthorize("hasAuthority('sys:user:edit')")
	public String edit(Model model, SysUser editObj){
		List<SysRole> roleList = this.sysRoleService.queryAll();
		model.addAttribute("roleList", roleList);
		model.addAttribute("editObj", editObj);
		return "sys-user/edit";
	}

	/**
	 * @description 编辑/添加 保存
	 * @return com.flouis.demo.base.result.JsonResult
	 */
	@PostMapping("/save")
	@ResponseBody
	public JsonResult save(@RequestBody SysUser sysUser){
		return this.sysUserService.save(sysUser);
	}

	/**
	 * @description 修改状态：冻结/开放/（逻辑）删除
	 * @param id 用户id
	 * @param state 修改之后的状态
	 */
	@PostMapping("/changeState")
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:user:del')")
	public JsonResult changeState(Long id, Integer state){
		return this.sysUserService.changeState(id, state);
	}

}
