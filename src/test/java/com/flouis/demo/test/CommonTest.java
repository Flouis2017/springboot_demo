package com.flouis.demo.test;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.List;

public class CommonTest {

	private long x;

	@Test
	public void test(){
		System.out.println("Hello springboot!");
		System.out.println(x);

		List<String> list = Lists.newArrayList();
		System.out.println(list.isEmpty());
	}

	@Test
	public void md5Test(){
		System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()).equals("e10adc3949ba59abbe56e057f20f883e"));
	}

	@Test
	public void bcryptTest(){
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}

}
