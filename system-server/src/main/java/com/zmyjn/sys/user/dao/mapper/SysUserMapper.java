package com.zmyjn.sys.user.dao.mapper;

import com.zmyjn.sys.user.entity.SysUser;
import com.zmyjn.core.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 系统用户信息
 * @author: Administrator
 * @date: 2019-03-30 11:35:57
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser,Integer>{
	
}
