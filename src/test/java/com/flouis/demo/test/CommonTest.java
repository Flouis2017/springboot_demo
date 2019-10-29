package com.flouis.demo.test;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

public class CommonTest {

	private long x;

	@Test
	public void test(){
		System.out.println("Hello springboot!");
		System.out.println(x);
	}

	@Test
	public void md5Test(){
		System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()).equals("e10adc3949ba59abbe56e057f20f883e"));
	}

}
