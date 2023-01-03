package com.gdsig.consumer.filter;

import com.gdsig.common.result.CommonResult;
import com.gdsig.common.result.RespCodeEnum;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xs
 * @date 2022/11/30下午 3:39
 */

@ControllerAdvice
public class FilterExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    CommonResult<String> errorHandler(Exception ex) {
        if (ex instanceof RuntimeException) {
            System.out.println(ex.getMessage());
            return CommonResult.fail("服务异常");
        } else if (ex instanceof MissingServletRequestParameterException) {
            return CommonResult.fail("参数：" + ((MissingServletRequestParameterException) ex).getParameterName() + "缺失");
        }else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
            FieldError error = e.getBindingResult().getFieldError();
            assert error != null;
            return CommonResult.fail(error.getDefaultMessage());
        } else {
            return CommonResult.fail(ex.getMessage());
        }
    }
}
