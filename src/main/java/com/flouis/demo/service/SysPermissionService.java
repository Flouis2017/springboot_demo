package com.flouis.demo.service;

import com.flouis.demo.base.result.JsonResult;
import com.flouis.demo.entity.SysPermission;
import com.flouis.demo.entity.SysRolePermission;
import com.flouis.demo.mapper.SysPermissionMapper;
import com.flouis.demo.mapper.SysRolePermissionMapper;
import com.flouis.demo.util.TreeUtil;
import com.flouis.demo.vo.ZTree;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysPermissionService {

	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;

	public JsonResult getTree() {
		List<SysPermission> allList = this.sysPermissionMapper.queryAll();
//		JDK8递归，返回标准数据结构的数据
//		TreeUtil.setPermissionTree(0L, allList, tree);
//		return JsonResult.success(allList);

//		返回简单数据结构的数据(列表)->转换成标准数据结构的数据交由ztree完成
		return JsonResult.success(TreeUtil.getSimplePermissionTree(allList));

//		传统递归，返回标准数据结构的数据
//		return JsonResult.success(TreeUtil.buildPermissionTree(TreeUtil.getZTreeRoot(), TreeUtil.toZTreeList(allList)));
	}

	public List<SysRolePermission> queryByRoleId(Long roleId) {
		return this.sysRolePermissionMapper.queryByRoleId(roleId);
	}

	public List<SysPermission> queryAll() {
		return this.sysPermissionMapper.queryAll();
	}

	public SysPermission queryById(Long id) {
		return this.sysPermissionMapper.selectByPrimaryKey(id);
	}


	public JsonResult getParentTree() {
		List<ZTree> allList = TreeUtil.getSimplePermissionTree(this.sysPermissionMapper.queryAll());

		List<ZTree> resList = Lists.newArrayList();
		// 二重循环提出叶节点
		int length = allList.size();
		for (int i = 0; i < length; i++){
			ZTree x = allList.get(i);
			for (int j = 0; j < length; j++){
				if (i == j){
					continue;
				}
				ZTree y = allList.get(j);
				if (x.getId().equals(y.getParentId())){
					resList.add(x);
					break;
				}
			}
		}
		return JsonResult.success(resList);
	}

	@Transactional
	public JsonResult save(SysPermission saveObj) {
		try {
			Long id = saveObj.getId();
			if (id == null){
				this.sysPermissionMapper.insertSelective(saveObj);
			} else {
				this.sysPermissionMapper.updateByPrimaryKeySelective(saveObj);
			}
			return JsonResult.success("保存成功");
		} catch (Exception e){
			e.printStackTrace();
			return JsonResult.fail("服务器异常，保存失败！");
		}
	}

	@Transactional
	public JsonResult del(Long id) {
		try {
			if (id == null){
				return JsonResult.fail("该记录不存在，删除失败！");
			}
			// 删除sys_permission记录
			this.sysPermissionMapper.deleteByPrimaryKey(id);
			// 删除sys_role_permission记录
			this.sysRolePermissionMapper.deleteByPermissionId(id);
			return JsonResult.success("删除成功");
		} catch (Exception e){
			e.printStackTrace();
			return JsonResult.fail("服务器异常，删除失败！");
		}
	}

	public JsonResult hasChildren(Long id) {
		return this.sysRolePermissionMapper.getChildrenCnt(id) > 0 ? JsonResult.success(true) : JsonResult.success(false);
	}

	/**
	 * @description 通过用户id获取一级菜单——模块菜单
	 * @param userId
	 * @return List<SysPermission>
	 */
	public List<SysPermission> getFirstMenus(Long userId) {
		return this.sysPermissionMapper.queryFirstMenusByUserId(userId);
	}

	/**
	 * @description 通过用户id获取二级菜单——页面菜单
	 * @param userId
	 * @return List<SysPermission>
	 */
	public List<SysPermission> getSecondMenus(Long userId) {
		return this.sysPermissionMapper.querySecondMenusByUserId(userId);
	}

}
