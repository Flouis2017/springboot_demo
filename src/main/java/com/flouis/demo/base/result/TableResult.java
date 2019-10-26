package com.flouis.demo.base.result;

import com.flouis.demo.base.enumeration.RespCode;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @date 20191026
 * @description layui数据表格
 */
@Getter
@Setter
@ToString
public class TableResult {

	private int code;
	private String msg = "";
	private long count;
	private Object data = Lists.newArrayList();

	private TableResult(){}

	private TableResult(int code, String msg, long count, Object data){
		this.setCode(code);
		this.setMsg(msg);
		this.setCount(count);
		this.setData(data);
	}

	public static TableResult result(int code, String msg, long count, Object data){
		return new TableResult(code, msg, count, data);
	}

	public static TableResult success(long count, Object data){
		return new TableResult(RespCode.SUCCESS.getCode(), RespCode.SUCCESS.getDes(), count, data);
	}

	public static TableResult fail(){
		return new TableResult(RespCode.FAIL.getCode(), RespCode.FAIL.getDes(), 0, Lists.newArrayList());
	}

	public static TableResult fail(String msg){
		return new TableResult(RespCode.FAIL.getCode(), msg, 0, Lists.newArrayList());
	}

}
