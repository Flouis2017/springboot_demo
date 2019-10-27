package com.flouis.demo.mapper;

import com.flouis.demo.entity.SysUser;
import com.flouis.demo.vo.SysUserVo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    List<SysUser> queryList(SysUserVo vo);
}