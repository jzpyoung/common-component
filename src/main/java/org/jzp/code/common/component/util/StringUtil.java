package org.jzp.code.common.component.util;

/**
 * 字符串工具类
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-09-27
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    public static String toBinaryString(long l) {
        return toBinaryString(l, 64, '0');
    }

    public static String toBinaryString(long l, int length) {
        return toBinaryString(l, length, '0');
    }

    public static String toBinaryString(long l, int length, char replaceChar) {
        String bs = Long.toBinaryString(l);
        bs = String.format("%" + length + "s", bs);
        return bs.replace(' ', replaceChar);
    }
}
