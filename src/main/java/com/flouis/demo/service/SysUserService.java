package com.flouis.demo.service;

import com.flouis.demo.entity.SysUser;
import com.flouis.demo.mapper.SysUserMapper;
import com.flouis.demo.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	public SysUser getById(Long id) {
		return this.sysUserMapper.selectByPrimaryKey(id);
	}

	public List<SysUser> queryList(SysUserVo vo) {
		return this.sysUserMapper.queryList(vo);
	}


}
