
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
				// pIdKey: "pId",
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
 * @description 获取所有权限-树形结构返回
 */
function getPermissionTree() {
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
			var data = res.data;
			var length = data.length;
			var children = [];
			for (var i = 0; i < length; i++){
				children[i] = createNode(data[i]);
			}
			root.children = children;
		}
	});

	return root;
}

/**
 * @description 节点创建-递归
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
