package com.cheetah.shiroandjwt.common;


/**
 * @author cjbi@outlook.com
 */
public enum CommonResultStatus implements ResultStatus {

    USERNAME_ERROR(HttpStatus.ERROR, "用户名不正确"),
    PASSWORD_ERROR(HttpStatus.ERROR, "密码不正确"),

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
