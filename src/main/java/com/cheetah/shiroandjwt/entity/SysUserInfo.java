package com.cheetah.shiroandjwt.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value="sys_user_info")
public class SysUserInfo implements Serializable {

    @TableId(value="id")
    private Long id;

    private String name;

    private String password;

    private Long roleId;

    private static final long serialVersionUID = 1L;

}