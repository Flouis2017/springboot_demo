package com.flouis.demo.service;

import com.flouis.demo.entity.SysUser;
import com.flouis.demo.mapper.SysPermissionMapper;
import com.flouis.demo.mapper.SysUserMapper;
import com.flouis.demo.vo.LoginUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = this.sysUserMapper.queryByUsername(username);

		// 用户名认证处理：
		if (user == null){
			throw new AuthenticationCredentialsNotFoundException("用户名不存在！");
		}
		if (user.getState() == 0){
			throw new LockedException("当前用户已被冻结，请联系超级管理员！");
		}
		if (user.getState() == 2){
			throw new AuthenticationServiceException("当前用户不可用！");
		}

		LoginUser loginUser = new LoginUser();
		BeanUtils.copyProperties(user, loginUser);

		loginUser.setPermissions(this.sysPermissionMapper.queryListByUserId(user.getId()));

		return loginUser;
	}

}
