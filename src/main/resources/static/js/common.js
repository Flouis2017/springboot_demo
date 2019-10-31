// @attention 使用脚本需先引入jquery.js

// 表单序列化
$.fn.serializeObject = function () {
	var ct = this.serializeArray();
	var obj = {};
	$.each(ct, function () {
		if (obj[this.name] !== undefined) {
			if (!obj[this.name].push) {
				obj[this.name] = [obj[this.name]];
			}
			obj[this.name].push($.trim(this.value) || "");
		} else {
			obj[this.name] = $.trim(this.value) || "";
		}
	});
	return obj;
};

/**
 * @description layui常用模块导出
 */
layui.define(['table', 'form'], function(exports){
	exports('getTable', function () {
		return layui.table;
	});
	exports('getForm', function () {
		return layui.form;
	});
});

/**
 * @description layui table的刷新操作
 */

// 当前页刷新
function tableReloadCurr(tableId, paramObj){
	layui.getTable().reload(tableId, {
		where: paramObj
	});
}

// 从首页刷新
function tableReloadFirst(tableId, paramObj) {
	layui.getTable().reload(tableId, {
		where: paramObj,
		page: { curr: 1 }
	});
}