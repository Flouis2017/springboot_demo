package com.flouis.demo.mapper;

import com.flouis.demo.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

}