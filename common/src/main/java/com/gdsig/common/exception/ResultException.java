package com.gdsig.common.exception;

import com.gdsig.common.result.RespCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 常规的业务错误异常
 *
 * @author huihu
 * @date 2021-08-31
 */
public class ResultException extends Exception {

    private String message;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ResultException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> T requireNotBlank(T obj, String message) throws ResultException {
        if (obj == null || StringUtils.isBlank(obj.toString())) {
            throw new ResultException(message);
        }
        return obj;
    }

    public static <T> T requireNotBlank(T obj, RespCodeEnum respCodeEnum) throws ResultException {
        if (obj == null || StringUtils.isBlank(obj.toString())) {
            throw new ResultException(respCodeEnum.getMsg());
        }
        return obj;
    }

    public static <T> T requireNotEmpty(T obj, String message) throws ResultException {
        if (obj == null || StringUtils.isEmpty(obj.toString())) {
            throw new ResultException(message);
        }
        return obj;
    }

    public static <T> T requireNonNull(T obj, String message) throws ResultException {
        if (obj == null) {
            throw new ResultException(message);
        }
        return obj;
    }

    public static <T> T requireNonNull(T obj, RespCodeEnum respCodeEnum) throws ResultException {
        if (obj == null) {
            Objects.requireNonNull(respCodeEnum);
            throw new ResultException(respCodeEnum.getMsg());
        }
        return obj;
    }

    public static void throwWhenTrue(boolean bool, String message) throws ResultException {
        if (bool) {
            throw new ResultException(message);
        }
    }

    public static void validOrThrow(boolean bool, String message) throws ResultException {
        if (!bool) {
            throw new ResultException(message);
        }
    }

    public static void throwMsg(String message) throws ResultException {
        throw new ResultException(message);
    }

    public static void throwWhenTrue(boolean bool, RespCodeEnum respCodeEnum) throws ResultException {
        if (bool) {
            Objects.requireNonNull(respCodeEnum);
            throw new ResultException(respCodeEnum.getMsg());
        }
    }

}
