package com.flouis.demo.mapper;

import com.flouis.demo.entity.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(SysRolePermission key);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    int deleteByRoleId(@Param("roleId") Long roleId);

    int batchInsert(List<SysRolePermission> list);

    List<SysRolePermission> queryByRoleId(@Param("roleId") Long roleId);

	int deleteByPermissionId(@Param("permissionId") Long permissionId);

    int getChildrenCnt(@Param("parentId") Long parentId);
}