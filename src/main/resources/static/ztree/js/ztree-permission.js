
/**
 * @description 获取树形通用设置
 */
function getSetting(isRadioType) {
	var setting = {
		check: {
			enable: true,
			chkboxType: {
				"Y": "ps",
				"N": "ps"
			}
		},
		async: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "parentId",
				rootPId: 0
			}
		},
		callback: {
			onCheck: zTreeOnCheck
		}
	};
	if (isRadioType){
		setting.check = {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		}
	}
	return setting;
}

// ztree初始化后的回调方法
function zTreeOnCheck() {
	// do something...
}

/**
 * @description 获取权限树数据：简单数据结构(列表)/标准数据结构(Json)
 */
function getPermissionTree() {
	// ztree标准数据结构根节点：
	var root = {
		id: 0,
		name: "权限树",
		open: true
	};

	$.ajax({
		url: "/sys/permission/getTree",
		type: "get",
		contentType: "application/json; charset=utf-8",
		async: false,
		success: function (res) {
			// console.log(res);

			// ztree简单数据结构只需返回一个[{id: , parentId: name: ""}, {...}, ...]列表，ztree就会解析该列表构建树形数据
			// 传统递归构建权限树，返回标准数据结构的数据
			root = res.data;

			/* // 使用JDK8新特性构建权限树(标准数据结构)
			var data = res.data;
			var length = data.length;
			var children = [];
			for (var i = 0; i < length; i++){
				children[i] = createNode(data[i]);
			}
			root.children = children;*/
		}
	});

	return root;
}

/**
 * @description 获取父级权限树数据
 */
function getPermissionParentTree() {
	var root = null;
	$.ajax({
		url: "/sys/permission/getParentTree",
		type: "get",
		contentType: "application/json; charset=utf-8",
		async: false,
		success: function (res) {
			root = res.data;
		}
	});
	return root;
}

/**
 * @description 节点创建-使用JDK8新特性构建权限树(标准数据结构)
 */
function createNode(node) {
	var children = node.children;
	var length = children.length;
	if (length == 0){
		delete node.children;
		return node;
	} else {
		node.open = true;
		for (var i=0; i<length; i++){
			createNode(children[i]);
		}
	}
	return node;
}

/**
 * @description 获取选择节点id
 */
function getCheckedIds(treeId) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var nodes = treeObj.getCheckedNodes(true);
	var length = nodes.length;
	var ids = "";
	for (var i = 0; i < length; i++) {
		ids += nodes[i]["id"] + ",";
	}
	ids = ids.substring(0, ids.length -1);
	return ids;
}

/**
 * @description 当编辑操作时回显选中记录
 */
function showChecked(roleId, zTree) {
	$.ajax({
		type: "get",
		url: "/sys/permission/getIdListByRoleId?roleId=" + roleId,
		success: function (res) {
			var data = res.data;
			var length = data.length;
			var id = 0;
			var node = null;
			for (var i=0; i<length; i++){
				id = data[i]['permissionId'];
				node = zTree.getNodeByParam("id", id);
				if (node != null){
					zTree.checkNode(node, true);
				}
			}
		}
	});
}

/**
 * @description 单选权限树选中初始化
 */
function initParentTree(parentId, zTree) {
	var node = null;
	// 如果是添加操作，默认选中根节点作为父级
	if (parentId == null || parentId == "" || parentId == undefined){
		node = zTree.getNodeByParam("id", 0);
		if (node != null){
			zTree.checkNode(node, true);
		}
	} else {
		node = zTree.getNodeByParam("id", parentId);
		if (node != null){
			zTree.checkNode(node, true);
		}
	}
}
