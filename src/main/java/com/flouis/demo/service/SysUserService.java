package com.flouis.demo.service;

import com.flouis.demo.entity.SysUser;
import com.flouis.demo.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	public SysUser getById(Long id) {
		return this.sysUserMapper.selectByPrimaryKey(id);
	}

}
