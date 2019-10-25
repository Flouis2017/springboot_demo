package com.flouis.demo.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
@Slf4j
public class ErrorPageController {

	@RequestMapping("/{errCode}")
	public String errorPage(@PathVariable("errCode") Integer errCode){
		log.error("error code: " + errCode);
		return "error/error";
	}

}
