package com.cheetah.shiroandjwt.service.impl;

import com.cheetah.shiroandjwt.common.CommonResultStatus;
import com.cheetah.shiroandjwt.entity.SysUserInfo;
import com.cheetah.shiroandjwt.entity.dto.UserTokenDTO;
import com.cheetah.shiroandjwt.exception.BusinessException;
import com.cheetah.shiroandjwt.jwt.JwtUtil;
import com.cheetah.shiroandjwt.mapper.SysUserInfoMapper;
import com.cheetah.shiroandjwt.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

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
    public UserTokenDTO login(UserTokenDTO userInfo) {
        // 从数据库获取对应用户名密码的用户
        SysUserInfo uInfo = userInfoMapper.getUserByLogin(userInfo.getName());
        if (null == uInfo) {
            throw new BusinessException(CommonResultStatus.USERNAME_ERROR);
        } else if (!userInfo.getPassword().equals(uInfo.getPassword())) {
            throw new BusinessException(CommonResultStatus.PASSWORD_ERROR);
        }
        //生成jwtToken
        userInfo.setToken(JwtUtil.sign(userInfo.getName(),String.valueOf(System.currentTimeMillis())));
        return userInfo;
    }




}
