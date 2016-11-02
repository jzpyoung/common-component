package org.jzp.code.common.component.enums;

/**
 * 标志枚举
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-10-26
 */
public enum FlagEnum {

    ZERO(0), ONE(1);

    private int value;

    FlagEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String getStrValue() {
        return this.value + "";
    }

    public static Boolean flag(int value) {
        FlagEnum flagEnum = from(value);
        switch (flagEnum) {
            case ZERO:
                return Boolean.FALSE;
            case ONE:
                return Boolean.TRUE;
            default:
                return Boolean.TRUE;
        }
    }

    public static Boolean flag(FlagEnum flagEnum) {
        return flag(flagEnum.getValue());
    }

    public static FlagEnum from(int value) {
        for (FlagEnum flagEnum : FlagEnum.values()) {
            if (flagEnum.getValue() == value) {
                return flagEnum;
            }
        }
        throw new IllegalArgumentException("非法的参数类型");
    }
}