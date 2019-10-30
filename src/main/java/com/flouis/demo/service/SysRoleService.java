package com.flouis.demo.service;

import com.flouis.demo.entity.SysRole;
import com.flouis.demo.mapper.SysRoleMapper;
import com.flouis.demo.vo.SysRoleVo;
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

	public List<SysRole> queryList(SysRoleVo vo) {
		return this.sysRoleMapper.queryList(vo);
	}

	public SysRole queryById(Long id) {
		return this.sysRoleMapper.selectByPrimaryKey(id);
	}
}
