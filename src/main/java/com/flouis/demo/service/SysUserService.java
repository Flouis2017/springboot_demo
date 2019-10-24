package com.flouis.demo.service;

import com.flouis.demo.entity.SysUser;
import com.flouis.demo.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserService {

	@Resource
	private SysUserMapper sysUserMapper;

	public SysUser getById(Long id) {
		return this.sysUserMapper.selectByPrimaryKey(id);
	}

}
