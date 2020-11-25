package com.cheetah.shiroandjwt.common;


/**
 * @author cjbi@outlook.com
 */
public enum CommonResultStatus implements ResultStatus {

    LOGIN_ERROR(HttpStatus.UNAUTHORIZED, "请先登陆"),
    USERNAME_ERROR(1001, "用户名不正确"),
    PASSWORD_ERROR(1002, "密码不正确"),
    TOKEN_CHECK_ERROR(1003, "Token校验不正确"),
    ENTITY_NOT_EXIST(1004, "实体类不存在"),

    ;

    private int code;
    private String message;

    CommonResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
