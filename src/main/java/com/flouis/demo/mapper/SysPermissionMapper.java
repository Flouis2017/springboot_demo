package com.flouis.demo.mapper;

import com.flouis.demo.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

	List<SysPermission> queryAll();

	List<SysPermission> queryFirstMenusByUserId(@Param("userId") Long userId);

	List<SysPermission> querySecondMenusByUserId(@Param("userId") Long userId);

	List<SysPermission> queryListByUserId(@Param("userId") Long userId);
}