package com.ucar.qtcassist.base.common.constant;

/**
 * Description: 业务异常信息接口，构造业务异常BusinessRuntimeException时传入
 *
 * @Version 1.0 2018/1/13 14:16 by 李超（lichao09@zuche.com）创建
 */
public interface BusinessExceptionEnumValue {
    /**
     * Description: 获取异常代码
     *
     * @version 1.0 2018/1/13 14:17 by 李超（chao.li01@ucarinc.com）创建
     */
    String getCode();

    /**
     * Description: 获取异常信息
     *
     * @version 1.0 2018/1/13 14:17 by 李超（chao.li01@ucarinc.com）创建
     */
    String getMessage();
}
