package com.flouis.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flouis.demo.entity.SysPermission;

import java.util.List;

/**
 * @description 树形结构工具类
 */
public class TreeUtil {

	/**
	 * @description 设置菜单树-递归
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

}
