package com.flouis.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemController {

	@RequestMapping("/")
	public String login(){
		return "login";
	}

}
