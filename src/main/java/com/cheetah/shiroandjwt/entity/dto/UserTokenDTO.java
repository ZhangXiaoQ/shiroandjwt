package com.cheetah.shiroandjwt.entity.dto;

import lombok.Data;

@Data
public class UserTokenDTO {

    private Long id;
    private String name;
    private String password;
    private String token;
}
