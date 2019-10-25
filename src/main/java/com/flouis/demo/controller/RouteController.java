package com.flouis.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/route")
public class RouteController {

	@RequestMapping("/getPage")
	public ModelAndView getPage(ModelAndView modelAndView, String location){
		modelAndView.setViewName(location);
		return modelAndView;
	}

}
