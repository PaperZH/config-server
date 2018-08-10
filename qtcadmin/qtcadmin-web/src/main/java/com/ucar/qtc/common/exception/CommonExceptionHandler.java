package com.ucar.qtc.common.exception;

import com.ucar.qtc.common.utils.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: cong.zhou01
 * @description: 内部异常
 * @date: 2018/8/7 10:19
 */
@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(Exception.class)
    ResponseResult exception(Exception e) {
        return ResponseResult.error(500, e.getMessage());
    }
}
