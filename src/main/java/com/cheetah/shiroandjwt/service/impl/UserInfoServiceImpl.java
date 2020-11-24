package com.cheetah.shiroandjwt.service.impl;

import com.cheetah.shiroandjwt.entity.SysUserInfo;
import com.cheetah.shiroandjwt.mapper.SysUserInfoMapper;
import com.cheetah.shiroandjwt.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: UserInfoServiceImpl
 * @Description: TODO
 * @Date: 2020/9/25
 * @Author: cheetah
 * @Version: 1.0
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private SysUserInfoMapper userInfoMapper;

    @Override
    public SysUserInfo getUserByLogin(String username) {
        return userInfoMapper.getUserByLogin(username);
    }
}
