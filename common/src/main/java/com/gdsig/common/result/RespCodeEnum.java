package com.gdsig.common.result;

/**
 * 响应码 - 枚举
 *
 * @author Administrator
 * @date 2021-08-25
 */

public enum RespCodeEnum {

    /**
     *
     */
    Success("000000", "成功"),
    Invalid("555555", "失败"),
    Fail("999999", "系统异常，请稍后重试。"),
    ParaRequired("999998", "参数缺失"),
    ParaInvalid("999997", "参数无效"),
    InvalidData("999994", "数据无效"),
    AccountInvalid("999990", "账号无效"),
    ToKenInvalid("777777", "登录状态已过期"),

    ;

    private final String code;
    private final String msg;


    RespCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    @Override
    public String toString() {
        return msg;
    }

}
