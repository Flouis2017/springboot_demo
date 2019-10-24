package com.flouis.demo.controller;

import com.flouis.demo.base.JsonResult;
import com.flouis.demo.entity.SysUser;
import com.flouis.demo.service.SysUserService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("/hello")
	@ResponseBody
	public Map hello(String name){
		log.info("execute UserController->hello");
		Map resMap = Maps.newHashMap();
		resMap.put("msg", "hello " + name + "!");
		return resMap;
	}

	@RequestMapping("/get")
	@ResponseBody
	public JsonResult getById(Long id){
		SysUser sysUser = this.sysUserService.getById(id);
		return  JsonResult.success(sysUser);
	}

}
