package com.flouis.demo.controller;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/hello")
	@ResponseBody
	public Map hello(String name){
		Map resMap = Maps.newHashMap();
		resMap.put("msg", "hello " + name + "!");
		return resMap;
	}

}
