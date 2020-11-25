package com.cheetah.shiroandjwt.service;


import com.cheetah.shiroandjwt.entity.SysUserInfo;
import com.cheetah.shiroandjwt.entity.dto.UserTokenDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @ClassName: UserInfoService
 * @Description: 用户service
 * @Date: 2020/9/25
 * @Author: cheetah
 * @Version: 1.0
 */
public interface UserInfoService {

    //用户登陆添加token信息
    UserTokenDTO login(UserTokenDTO userTokenDTO);

}
