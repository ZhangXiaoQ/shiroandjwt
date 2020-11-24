package com.cheetah.shiroandjwt.service;


import com.cheetah.shiroandjwt.entity.SysUserInfo;

/**
 * @ClassName: UserInfoService
 * @Description: 用户service
 * @Date: 2020/9/25
 * @Author: cheetah
 * @Version: 1.0
 */
public interface UserInfoService {

    //通过用户名密码获取用户信息
    SysUserInfo getUserByLogin(String username);
}
