package com.flouis.demo.mapper;

import com.flouis.demo.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

	List<SysPermission> queryAll();
}