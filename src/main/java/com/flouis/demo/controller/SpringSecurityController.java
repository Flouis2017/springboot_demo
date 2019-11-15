package com.flouis.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.flouis.demo.base.result.JsonResult;
import com.flouis.demo.entity.SysPermission;
import com.flouis.demo.mapper.SysPermissionMapper;
import com.flouis.demo.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SpringSecurityController {

	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	@GetMapping("/login.html")
	public String loginHtml(){
		return "login";
	}

	@RequestMapping("/initLeftNavMenus")
	@ResponseBody
	public JsonResult initLeftNavMenus(Long userId){
		List<SysPermission> list = this.sysPermissionMapper.queryListByUserId(userId);
		list = list.stream().filter(p -> p.getType().equals(1)).collect(Collectors.toList());
		JSONArray array = new JSONArray();
		TreeUtil.setPermissionTree(0L, list, array);
		return JsonResult.success(array);
	}

	@GetMapping("/authority_error.html")
	public String authorityErrorHtml(){
		return "error/authority_error";
	}

}
