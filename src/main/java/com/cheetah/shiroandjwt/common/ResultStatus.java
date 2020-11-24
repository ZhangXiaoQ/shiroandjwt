package com.cheetah.shiroandjwt.common;

/**
 * @author cjbi
 */
public interface ResultStatus {
    /**
     * 错误码
     */
    int getCode();

    /**
     * 错误信息
     */
    String getMessage();
}
