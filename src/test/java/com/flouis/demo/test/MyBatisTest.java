package com.flouis.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.flouis.demo.entity.SysPermission;
import com.flouis.demo.entity.SysUser;
import com.flouis.demo.mapper.SysPermissionMapper;
import com.flouis.demo.mapper.SysUserMapper;
import com.flouis.demo.util.TreeUtil;
import com.flouis.demo.vo.ZTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	@Test
	public void queryTest(){
		SysUser sysUser = this.sysUserMapper.selectByPrimaryKey(1L);
		System.out.println(sysUser.toString());
	}

	@Test
	public void jdk8Test(){
		List<SysPermission> permissionList = this.sysPermissionMapper.queryAll();
		List<ZTree> zTreeList = TreeUtil.toZTreeList(permissionList);
		System.out.println("zTreeList: " + JSON.toJSONString(zTreeList));
		Optional<ZTree> res = zTreeList.stream().filter(z -> z.getId() > 1).findAny();
		System.out.println("res : " + res);
		Optional<ZTree> res2 = zTreeList.stream().filter(z -> z.getId() > 1).findFirst();
		System.out.println("res2: " + res2);
	}

	@Test
	public void buildPermissionTreeTest(){
		List<SysPermission> permissionList = this.sysPermissionMapper.queryAll();
		List<ZTree> zTreeList = TreeUtil.toZTreeList(permissionList);
		System.out.println("zTreeList: " + JSON.toJSONString(zTreeList));

		ZTree permissionTree = TreeUtil.buildPermissionTree(TreeUtil.getZTreeRoot(), zTreeList);
		System.out.println("permissionTree: " + JSON.toJSONString(permissionTree));
	}



}
