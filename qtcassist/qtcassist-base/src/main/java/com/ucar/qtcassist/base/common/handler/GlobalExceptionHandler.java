package com.ucar.qtcassist.base.common.handler;

import com.ucar.qtcassist.base.common.constant.ErrorEnum;
import com.ucar.qtcassist.api.model.BusinessRuntimeException;
import com.ucar.qtcassist.api.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 * Description: 全局异常处理
 *
 * @author wangyang
 * @since 2018/8/6
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler");

    /**
     * 处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessRuntimeException.class)
    public Result defaultErrorHandler(BusinessRuntimeException e) {
        String trace = e.getStackTrace().length>0 ? "" : e.getStackTrace()[0].toString();
        logger.error("返回业务异常" + e.getCode(), e);
        Result result = Result.getBusinessException(e.getCode(), e.getMessage(), trace);
        return result;
    }

    /**
     * 处理其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result defaultErrorHandler(Exception e) {
        String trace = e.getStackTrace().length>0 ? "" : e.getStackTrace()[0].toString();
        logger.error("返回服务异常", e);
        Result result = Result.getServiceError(ErrorEnum.E_99999.getCode(), e.getMessage(), trace);
        return result;
    }

    /**
     * GET/POST请求方法错误的拦截器
     * 因为开发时可能比较常见,而且发生在进入controller之前,上面的拦截器拦截不到这个错误
     * 所以定义了这个拦截器
     *
     * @return
     * @throws Exception
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodHandler(HttpRequestMethodNotSupportedException e) {
        String trace = e.getStackTrace().length>0 ? "" : e.getStackTrace()[0].toString();
        logger.error("不支持当前请求方法", e);
        Result result = Result.getServiceError(ErrorEnum.E_500.getCode(), e.getMessage(), trace);
        return result;
    }

}
