package com.flouis.demo.service;

import com.flouis.demo.base.result.JsonResult;
import com.flouis.demo.entity.SysRole;
import com.flouis.demo.entity.SysRolePermission;
import com.flouis.demo.mapper.SysRoleMapper;
import com.flouis.demo.mapper.SysRolePermissionMapper;
import com.flouis.demo.vo.SysRoleVo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;

	public List<SysRole> queryAll() {
		return this.sysRoleMapper.queryAll();
	}

	public List<SysRole> queryList(SysRoleVo vo) {
		return this.sysRoleMapper.queryList(vo);
	}

	public SysRole queryById(Long id) {
		return this.sysRoleMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public JsonResult save(SysRoleVo vo) {
		try {
			Long id = vo.getId();
			String name = vo.getName();
			// 去重校验
			SysRole record = this.sysRoleMapper.queryByName(name);
			if (id != null){
				// 编辑操作
				if (record != null && !record.getId().equals(vo.getId())){
					return JsonResult.fail("角色名不可重复！");
				}
				// 角色表更新
				SysRole upObj = new SysRole();
				upObj.setId(vo.getId());
				upObj.setDescription(vo.getDescription());
				upObj.setName(vo.getName());
				this.sysRoleMapper.updateByPrimaryKeySelective(upObj);
			} else {
				// 添加操作
				if (record != null){
					return JsonResult.fail("角色名不可重复！");
				}
				// 角色表插入
				SysRole sysRole = new SysRole();
				sysRole.setName(name);
				sysRole.setDescription(vo.getDescription());
				this.sysRoleMapper.insertSelective(sysRole);
				id = sysRole.getId();
			}
			// 更新角色-权限表(先删除在添加)
			this.sysRolePermissionMapper.deleteByRoleId(id);
			List<String> permissionIdList = Lists.newArrayList(vo.getPermissionIds().split(","));
			List<SysRolePermission> newList = Lists.newArrayList();
			for (String x : permissionIdList){
				Long permissionId = Long.valueOf(x);
				SysRolePermission newObj = new SysRolePermission();
				newObj.setRoleId(id);
				newObj.setPermissionId(permissionId);
				newList.add(newObj);
			}
			this.sysRolePermissionMapper.batchInsert(newList);

			return JsonResult.success("操作成功");
		} catch (Exception e){
			e.printStackTrace();
			return JsonResult.fail("服务器异常，操作失败！");
		}
	}

}
