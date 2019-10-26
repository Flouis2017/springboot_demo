package com.flouis.demo.base.enumeration;

public enum RespCode {

	SUCCESS(1000, "Request Success!"),
	FAIL(4000, "Errors occurred in server, Request Failed!");

	private int code;
	private String des;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	RespCode(int code, String des){
		this.setCode(code);
		this.setDes(des);
	}

}
