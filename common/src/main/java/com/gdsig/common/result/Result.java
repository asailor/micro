package com.gdsig.common.result;

import com.gdsig.common.exception.ResultException;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolationException;


/**
 * 带泛型的结果对象
 *
 * @param <T>
 * @author yhh
 * @date 2020-07-01
 */
public class Result<T> {

    private boolean result;
    private String code;
    private String message;
    private T data;

    /**
     * 默认成功的响应结果
     */
    public Result() {
        this.result = true;
        this.code = RespCodeEnum.Success.getCode();
        this.message = RespCodeEnum.Success.getMsg();
    }

    /**
     * 封装数据，默认result=true,code/message=EnumRespCode.Success
     *
     * @param data 泛型数据
     */
    public Result(T data) {
        this.result = true;
        this.code = RespCodeEnum.Success.getCode();
        this.message = "Success";
        this.data = data;
    }

    /**
     * 默认成功结果
     *
     * @param message 响应成功信息
     */
    public Result(String message) {
        this.result = true;
        this.code = RespCodeEnum.Success.getCode();
        this.message = message;
    }

    /**
     * 无需封装数据
     *
     * @param result  true/false
     * @param code    响应码
     * @param message 响应信息
     */
    public Result(boolean result, String code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

    /**
     * 无需封装数据，code根据result赋值EnumRespCode.Success/EnumRespCode.Fail
     *
     * @param result  true/false
     * @param message 响应信息
     */
    public Result(boolean result, String message) {
        this.result = result;
        this.code = result ? RespCodeEnum.Success.getCode() : RespCodeEnum.Fail.getCode();
        this.message = message;
    }

    /**
     * 封装数据，code根据result赋值EnumRespCode.Success/EnumRespCode.Fail
     *
     * @param result  true/false
     * @param message 响应信息
     * @param data    泛型数据
     */
    public Result(boolean result, String message, T data) {
        this.result = result;
        this.code = RespCodeEnum.Success.getCode();
        this.message = message;
        this.data = data;
    }

    /**
     * 封装数据
     *
     * @param result  true/false
     * @param code    响应码
     * @param message 响应信息
     * @param data    泛型数据
     */
    public Result(boolean result, String code, String message, T data) {
        this.result = result;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造枚举类响应结果
     *
     * @param result       true/false
     * @param respCodeEnum 枚举类响应信息
     */
    public Result(boolean result, RespCodeEnum respCodeEnum) {
        this.result = result;
        this.code = respCodeEnum.getCode();
        this.message = respCodeEnum.getMsg();
    }

    /**
     * 构造异常枚举类响应结果
     *
     * @param e 异常
     */
    public Result(Exception e) {
        this.result = false;
        this.code = RespCodeEnum.Fail.getCode();

        if (e instanceof ResultException) {
            this.message = e.getMessage();
        } else if (e instanceof ConstraintViolationException) {
            this.message = StringUtils.substringAfterLast(e.getMessage(), ":");
        } else {
            e.printStackTrace();
            this.message = RespCodeEnum.Fail.getMsg();
        }
    }

    public boolean isSuccess() {
        return result;
    }

    public boolean isNotSuccess() {
        return !result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public T getData(T def) {
        return data != null ? data : def;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result [result=" + result + ", code=" + code + ", message=" + message + "]";
    }

}
