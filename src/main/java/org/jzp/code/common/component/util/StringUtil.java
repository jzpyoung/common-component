package org.jzp.code.common.component.util;

import org.jzp.code.common.component.constant.Constants;

/**
 * 字符串工具类
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-09-27
 */
public class StringUtil {

    private static final int BINARY_BITS = 64;

    private static final char EMPTY_REPLACE_CHAR = '0';

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    public static String toBinaryString(long l) {
        return toBinaryString(l, BINARY_BITS, EMPTY_REPLACE_CHAR);
    }

    public static String toBinaryString(long l, int length) {
        return toBinaryString(l, length, EMPTY_REPLACE_CHAR);
    }

    public static String toBinaryString(long l, int length, char replaceChar) {
        String bs = Long.toBinaryString(l);
        bs = String.format("%" + length + "s", bs);
        return bs.replace(Constants.EMPTYCHAR, replaceChar);
    }
}
