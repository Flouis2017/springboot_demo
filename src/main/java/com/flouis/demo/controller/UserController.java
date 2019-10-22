package com.flouis.demo.controller;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

	@RequestMapping("/user/hello")
	public Map hello(String name){
		Map resMap = Maps.newHashMap();
		resMap.put("msg", "hello " + name + "!");
		return resMap;
	}

}
