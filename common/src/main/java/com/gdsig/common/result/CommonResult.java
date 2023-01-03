package com.gdsig.common.result;


import lombok.Data;

/**
 * 带泛型的结果对象
 *
 * @param <T>
 * @author yhh
 * @date 2020-07-01
 */
@Data
public class CommonResult<T> {

    private String code;
    private String message;
    private T data;

    /**
     * 默认成功的响应结果
     */
    public CommonResult() {
    }

    /**
     * 封装数据
     *
     * @param code    响应码
     * @param message 响应信息
     * @param data    泛型数据
     */
    public CommonResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * @param result 结果
     * @param <T>    T
     */
    public static <T> CommonResult<T> get(Result<T> result) {
        return new CommonResult<>(result.getCode(), result.getMessage(), result.getData());
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> ok(T data) {
        return new CommonResult<>(RespCodeEnum.Success.getCode(), RespCodeEnum.Success.getMsg(), data);
    }

    /**
     * 成功返回结果
     *
     * @param message 提示信息
     * @param data    获取的数据
     */
    public static <T> CommonResult<T> ok(String message, T data) {
        return new CommonResult<>(RespCodeEnum.Success.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> fail(String message) {
        return new CommonResult<>(RespCodeEnum.Fail.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized() {
        return new CommonResult<>(RespCodeEnum.ToKenInvalid.getCode(), RespCodeEnum.ToKenInvalid.getMsg(), null);
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", message=" + message + "]";
    }

}
