package com.flouis.demo.mapper;

import com.flouis.demo.entity.SysRole;
import com.flouis.demo.vo.SysRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

	List<SysRole> queryAll();

	List<SysRole> queryList(SysRoleVo vo);

	SysRole queryByName(@Param("name") String name);
}