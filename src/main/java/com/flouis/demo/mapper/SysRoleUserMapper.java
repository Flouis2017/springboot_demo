package com.flouis.demo.mapper;

import com.flouis.demo.entity.SysRoleUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleUserMapper {
    int deleteByPrimaryKey(SysRoleUser key);

    int insert(SysRoleUser record);

    int updateByUserId(@Param("userId") Long userId, @Param("roleId") Long roleId);
}