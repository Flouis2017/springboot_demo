package com.flouis.demo.security;

import com.flouis.demo.security.authentication.MyAuthenticationFailureHandler;
import com.flouis.demo.security.authentication.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private MyAuthenticationSuccessHandler successHandler;

	@Autowired
	private MyAuthenticationFailureHandler failureHandler;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/**
	 * @description 密码认证
	 * @param auth
	 * @throws Exception
	 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * @description http请求拦截，antMatchers()方法进行放行
	 * @param http
	 * @throws Exception
	 */
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/login.html",
					"/imgs/**",
					"/js/**",
					"/treetable-lay/**",
					"/xadmin/**",
					"/ztree/**",
					"/statics/**")
			.permitAll()
			.anyRequest()
			.authenticated();

		// 解决 X-Frame-Options deny 问题
		http.headers().frameOptions().sameOrigin();

		http.formLogin()
			.loginPage("/login.html")
			.loginProcessingUrl("/doLogin")
			.successHandler(successHandler)
			.failureHandler(failureHandler);
	}

}
