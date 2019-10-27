package com.flouis.demo.mapper;

import com.flouis.demo.entity.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(SysRolePermission key);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);
}