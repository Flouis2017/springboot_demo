package com.flouis.demo.controller;

import com.flouis.demo.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/route")
public class RouteController {

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping("/getPage")
	public ModelAndView getPage(ModelAndView modelAndView, String location){
		modelAndView.setViewName(location);
		// 跳转至用户列表页时要回传一个角色列表
		if (location.endsWith("sys-user/list")){
			modelAndView.addObject("roleList", this.sysRoleService.queryAll());
		}
		return modelAndView;
	}

}
