package com.flouis.demo.test;

import com.flouis.demo.entity.SysUser;
import com.flouis.demo.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Test
	public void queryTest(){
		SysUser sysUser = this.sysUserMapper.selectByPrimaryKey(1L);
		System.out.println(sysUser.toString());
	}

}
