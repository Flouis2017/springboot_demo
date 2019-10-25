package com.flouis.demo.controller;

import com.flouis.demo.base.JsonResult;
import com.flouis.demo.entity.SysUser;
import com.flouis.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;

	/**
	 * @description springmvc测试
	 * @param name
	 * @param model
	 */
	@RequestMapping("/hello")
	public String hello(String name, Model model){
		model.addAttribute("name", name);
		return "sys-user/hello";
	}

	/**
	 * @description 列表页
	 */
	@RequestMapping("/list")
	public String list(){
		return "sys-user/list";
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
