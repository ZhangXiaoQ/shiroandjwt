package com.cheetah.shiroandjwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheetah.shiroandjwt.entity.SysUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface SysUserInfoMapper extends BaseMapper<SysUserInfo> {
    //通过用户名获取用户信息
    SysUserInfo getUserByLogin(String username);

}