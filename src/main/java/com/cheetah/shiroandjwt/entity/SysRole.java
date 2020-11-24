package com.cheetah.shiroandjwt.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value="sys_role")
public class SysRole implements Serializable {

    @TableId(value="id")
    private Long id;

    private String roleName;

    private static final long serialVersionUID = 1L;


}