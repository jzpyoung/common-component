package org.jzp.code.common.component.data;

/**
 * 公共返回码枚举
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-11-02
 */
public enum CommonCode {

    /**
     * 成功。
     */
    SUCCESS(200),

    /**
     * 失败。
     */
    FAIL(300),

    /**
     * 系统异常
     */
    SYSTEMEXCEPTION(1000),

    /**
     * 调用服务失败
     */
    CALLSERVICEEXCEPTION(1101),

    /**
     * 参数为空
     */
    PARAMATERSISNULL(2000),

    /**
     * 查询结果集为空
     */
    RESULTSETISNULL(2001),

    /**
     * 参数解析异常
     */
    PARAMPARSEEXCEPTION(2002),

    /**
     * 类名未找到
     */
    CLASSNOTFOUNDEXCEPTION(2003),

    /**
     * 反射类实例化失败
     */
    INSTANTIATIONEXCEPTION(2004),

    /**
     * 反射类非法接入
     */
    ILLEGALACCESSEXCEPTION(2005),

    /**
     * 格式化数据异常
     */
    FORMATDATAEXCEPTION(2006),

    /**
     * 配置异常
     */
    CONFIGEXCEPTION(2007);

    private int value;

    CommonCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
