package com.flouis.demo.controller;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

	@RequestMapping("/hello")
	@ResponseBody
	public Map hello(String name){
		log.info("execute UserController->hello");
		Map resMap = Maps.newHashMap();
		resMap.put("msg", "hello " + name + "!");
		return resMap;
	}

}
