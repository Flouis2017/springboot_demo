package com.flouis.demo.service;

import com.flouis.demo.base.result.JsonResult;
import com.flouis.demo.entity.SysRoleUser;
import com.flouis.demo.entity.SysUser;
import com.flouis.demo.mapper.SysRoleUserMapper;
import com.flouis.demo.mapper.SysUserMapper;
import com.flouis.demo.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysRoleUserMapper sysRoleUserMapper;

	public List<SysUser> queryList(SysUserVo vo) {
		return this.sysUserMapper.queryList(vo);
	}

	@Transactional
	public JsonResult save(SysUser sysUser) {
		try {
			Long userId = sysUser.getId();
			Long roleId = sysUser.getRoleId();

			if (roleId == null){
				return JsonResult.fail("角色参数出错，操作失败！");
			}

			// 数据落地
			if (userId != null){
				// 更新操作，只需校验邮箱是否已被占用
				SysUser user = this.sysUserMapper.queryByEmail(sysUser.getEmail());
				if (user != null && !userId.equals(user.getId())){
					return JsonResult.fail("邮箱已被占用！");
				}
				this.sysUserMapper.updateByPrimaryKeySelective(sysUser);
				this.sysRoleUserMapper.updateByUserId(userId, roleId);
			} else {
				// 添加操作，需要校验用户和邮箱是否已经存在
				SysUser user = this.sysUserMapper.queryByUsername(sysUser.getUsername());
				if (user != null){
					return JsonResult.fail("用户名已存在！");
				}
				user = this.sysUserMapper.queryByEmail(sysUser.getEmail());
				if (user != null){
					return JsonResult.fail("邮箱已被占用！");
				}
				// 对密码使用BCrypt加密处理
				sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
				this.sysUserMapper.insertSelective(sysUser);
				SysRoleUser newRecord = new SysRoleUser();
				newRecord.setRoleId(roleId);
				newRecord.setUserId(sysUser.getId());
				this.sysRoleUserMapper.insert(newRecord);
			}
			return JsonResult.success("操作成功");
		} catch (Exception e){
			e.printStackTrace();
			return JsonResult.fail("服务器异常，操作失败！");
		}
	}

	public JsonResult changeState(Long id, Integer state) {
		try {
			SysUser upObj = new SysUser();
			upObj.setId(id);
			upObj.setState(state);
			this.sysUserMapper.updateByPrimaryKeySelective(upObj);
			return JsonResult.success("操作成功");
		} catch (Exception e){
			e.printStackTrace();
			return JsonResult.fail("服务器异常，操作失败！");
		}
	}

}
