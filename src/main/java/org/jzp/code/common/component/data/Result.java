package org.jzp.code.common.component.data;

/**
 * 结果枚举
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-11-02
 */
public enum Result {

    /**
     * 成功。
     */
    SUCCESS(1),

    /**
     * 失败。(表示逻辑上的错误)
     */
    FAILED(0),

    /**
     * 错误。
     */
    ERROR(-1);

    private int value;

    Result(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean isSuccess(Result result) {
        if (result == Result.SUCCESS) {
            return true;
        }
        return false;
    }
}
