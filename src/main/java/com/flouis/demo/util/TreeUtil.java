package com.flouis.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flouis.demo.entity.SysPermission;
import com.flouis.demo.vo.ZTree;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @description 树形结构工具类
 */
public class TreeUtil {

	/**
	 * @description 设置权限树-使用JDK8新特性递归
	 */
	public static void setPermissionTree(Long parentId, List<SysPermission> allList, JSONArray tree){
		for (SysPermission per : allList){
			if (per.getParentId().equals(parentId)){
				JSONObject parent = JSON.parseObject(JSON.toJSONString(per));
				tree.add(parent);
				// 寻找所有子节点(JDK8新特性)，进行递归
				if (allList.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null){
					JSONArray children = new JSONArray();
					parent.put("children", children);
					setPermissionTree(per.getId(), allList, children);
				}
			}
		}
	}

	/**
	 * @description 使用ztree简单数据结构构建权限树时，我们只需要返回一个[{id: , parentId: name: ""}, {...}, ...]格式的列表，
	 * 在前端ztree就会解析该列表，完成权限树的构建
	 */
	public static List<ZTree> getSimplePermissionTree(List<SysPermission> allList){
		ZTree root = new ZTree(0L, null, "权限树", true, null);
		List<ZTree> tree = Lists.newArrayList();
		tree.add(root);
		for (SysPermission sp : allList){
			tree.add(toZTree(sp));
		}
		return tree;
	}

	/**
	 * @description 构建权限树
	 */
	public static ZTree buildPermissionTree(ZTree parent, List<ZTree> zTreeList) {
		/*List<ZTree> children = Lists.newArrayList();
		parent.setChildren(children);
		for (ZTree x : zTreeList){
			if (x.getParentId().equals(parent.getId())){
				children.add(x);

			}
		}*/
		return parent;
	}

	public static ZTree getZTreeRoot(){
		return new ZTree(0L, null, "权限树", true, Lists.newArrayList());
	}

	/**
	 * @description 权限转为ZTree对象
	 */
	public static ZTree toZTree(SysPermission sp){
		ZTree zTree = new ZTree(sp.getId(), sp.getParentId(), sp.getName(), true, null);
		return zTree;
	}

	/**
	 * @description 权限列表转为ZTree列表
	 */
	public static List<ZTree> toZTreeList(List<SysPermission> list){
		List<ZTree> resList = Lists.newArrayList();
		for (SysPermission sp : list){
			resList.add(toZTree(sp));
		}
		return resList;
	}

}
