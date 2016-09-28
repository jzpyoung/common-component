package org.jzp.code.common.component.code;

/**
 * 返回码枚举
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-09-27
 */
public enum BizCode {

    BIZ_CHECK_PARAM_IS_NULL(1000, "参数为空"),

    BIZ_REGEX_MATCH_NUM_NOT_EQUAL_EXCEPTION(1001, "待替换字符串和传入替换字符串个数不相等"),

    BIZ_HTTP_PARAM_ASSEMBLY_EXCEPTION(1002, "HTTP参数组装错误");

    private int value;
    private String message;

    BizCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
