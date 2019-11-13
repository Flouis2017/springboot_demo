package com.flouis.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/xx")
public class SpringSecurityController {

	@RequestMapping("/login.html")
	public String toLogin(){
		return "login";
	}

}
