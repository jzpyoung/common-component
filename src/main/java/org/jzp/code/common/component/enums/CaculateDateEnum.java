package org.jzp.code.common.component.enums;

/**
 * 计算日期向前向后枚举类
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-09-27
 */
public enum CaculateDateEnum {

    // 向前
    BEFORE(-1),

    // 向后
    AFTER(1);

    private Integer value;

    CaculateDateEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
