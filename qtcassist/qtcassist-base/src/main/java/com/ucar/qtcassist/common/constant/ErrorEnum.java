package com.ucar.qtcassist.common.constant;

/**
 * @author: cong.zhou01
 * @description: 错误码定义
 * @date: 2018/8/2 10:19
 */
public enum ErrorEnum implements BusinessExceptionEnumValue {
    /*
     * 错误信息
     * */
    E_400("400", "请求处理异常，请稍后再试"),
    E_500("500", "请求方式有误,请检查 GET/POST"),
    E_501("501", "请求路径不存在"),
    E_502("502", "权限不足"),
    E_10008("10008", "角色删除失败,尚有用户属于此角色"),
    E_10009("10009", "账户已存在"),

    E_20011("20011", "登陆已过期,请重新登陆"),

    E_90003("90003", "缺少必填参数"),

    E_99999("99999", "内部异常");

    private String code;

    private String Message;


    ErrorEnum(String code, String Message) {
        this.code = code;
        this.code = Message;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
