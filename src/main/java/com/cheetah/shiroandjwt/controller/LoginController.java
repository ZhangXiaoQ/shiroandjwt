package com.cheetah.shiroandjwt.controller;

import com.cheetah.shiroandjwt.common.AjaxResult;
import com.cheetah.shiroandjwt.entity.SysUserInfo;
import com.cheetah.shiroandjwt.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LoginController
 * @Description: TODO
 * @Date: 2020/11/8
 * @Author: cheetah
 * @Version: 1.0
 */
@RestController
@RequestMapping("adminLogin")
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("login")
    public AjaxResult login(@RequestBody SysUserInfo userInfo){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getName(),userInfo.getPassword());
        subject.login(token);
        return AjaxResult.success("登陆成功");
    }
}
