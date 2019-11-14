package com.flouis.demo.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		logger.info("登录失败！");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		Map<String, Object> map = Maps.newHashMap();
		if (exception instanceof AuthenticationCredentialsNotFoundException){
			map.put("message", "用户名不存在！");
		} else if(exception instanceof BadCredentialsException){
			map.put("message", "用户名密码不匹配！");
		} else if(exception instanceof LockedException){
			map.put("message", "当前用户已被冻结，请联系超级管理员！");
		} else if(exception instanceof DisabledException){
			map.put("message", "当前用户不可用！");
		}
		response.getWriter().write(objectMapper.writeValueAsString(map));
	}

}
