package com.flouis.demo.service;

import com.flouis.demo.entity.SysRole;
import com.flouis.demo.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	public List<SysRole> queryAll() {
		return this.sysRoleMapper.queryAll();
	}

}
