package com.flouis.demo.controller;

import com.alibaba.fastjson.JSON;
import com.flouis.demo.base.result.JsonResult;
import com.flouis.demo.entity.SysRole;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/requestBody")
	@ResponseBody
	public JsonResult requestBodyTest(@RequestBody SysRole sysRole){
		System.out.println(JSON.toJSONString(sysRole));
		return JsonResult.success("Your request has been handled!");
	}

	@RequestMapping("/requestBody2")
	@ResponseBody
	public JsonResult requestBodyTest2(SysRole sysRole){
		System.out.println(JSON.toJSONString(sysRole));
		return JsonResult.success("Your request has been handled!");
	}

}
