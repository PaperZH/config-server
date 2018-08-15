package com.ucar.qtcassist.base.common.exception;

import com.ucar.qtcassist.base.common.constant.BusinessExceptionEnumValue;

/**
 * Description: 业务异常, 增加了code字段.
 * 如果在Controller中抛出,会使用Result.status=-1的结果.
 * All Rights Reserved.
 *
 * @version 1.0 2017-12-20 14:06:29 by 刘庆魁（qk.liu@zuche.com）
 * @version 1.1 2018/1/13 14:26 by 李超（chao.li01@ucarinc.com）增加入参BusinessExceptionEnumValue构造方法，各系统的异常信息实现此接口
 */
public class BusinessRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 2487325270865275804L;

    /**
     * 异常代码.
     */
    private String code;

    public BusinessRuntimeException() {
        super();
    }

    public BusinessRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessRuntimeException(String message) {
        super(message);
    }

    public BusinessRuntimeException(Throwable cause) {
        super(cause);
    }

    public BusinessRuntimeException(String message, String code) {
        super(message);
        this.code = code;
    }

    public BusinessRuntimeException(String message, String code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessRuntimeException(BusinessExceptionEnumValue value) {
        super(value.getMessage());
        this.code = value.getCode();
    }

    public BusinessRuntimeException(BusinessExceptionEnumValue value, Throwable cause) {
        super(value.getMessage(), cause);
        this.code = value.getCode();
    }

    public String getCode() {
        return code;
    }

}
