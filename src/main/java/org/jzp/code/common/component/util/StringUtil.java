package org.jzp.code.common.component.util;

import org.apache.commons.lang3.StringUtils;
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

    /**
     * 判断是否为空字符串
     *
     * @param str 待判断字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 数字转二进制字符串
     *
     * @param l 待转换数字
     * @return
     */
    public static String toBinaryString(long l) {
        return toBinaryString(l, BINARY_BITS, EMPTY_REPLACE_CHAR);
    }

    /**
     * 数字转二进制字符串
     *
     * @param l      待转换数字
     * @param length 转换后的字符串长度
     * @return
     */
    public static String toBinaryString(long l, int length) {
        return toBinaryString(l, length, EMPTY_REPLACE_CHAR);
    }

    /**
     * 数字转二进制字符串
     *
     * @param l           待转换数字
     * @param length      转换后的字符串长度
     * @param replaceChar 补位字符
     * @return
     */
    public static String toBinaryString(long l, int length, char replaceChar) {
        String bs = Long.toBinaryString(l);
        bs = String.format("%" + length + "s", bs);
        return bs.replace(Constants.EMPTYCHAR, replaceChar);
    }

    /**
     * 左部填充
     *
     * @param str          待处理字符串
     * @param rightKeepNum 保留右边位数
     * @param padStr       填充字符串
     * @return
     */
    public static String leftPad(String str, int rightKeepNum, String padStr) {
        if (isEmpty(str)) {
            return "";
        }
        String name = StringUtils.right(str, rightKeepNum);
        return StringUtils.leftPad(name, StringUtils.length(str), padStr);
    }

    /**
     * 中部填充
     *
     * @param str          待处理字符串
     * @param leftKeepNum  保留左边位数
     * @param rightKeepNum 保留右边位数
     * @param padStr       填充字符串
     * @return
     */
    public static String middlePad(String str, int leftKeepNum, int rightKeepNum, String padStr) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        int i = 0;
        String removeStr = "";
        while (i < leftKeepNum) {
            removeStr += padStr;
            i++;
        }
        return StringUtils.left(str, leftKeepNum)
                .concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(str, rightKeepNum), StringUtils.length(str), padStr), removeStr));
    }

    /**
     * 右部填充
     *
     * @param str         待处理字符串
     * @param leftKeepNum 保留左边位数
     * @param padStr      填充字符串
     * @return
     */
    public static String rightPad(String str, int leftKeepNum, String padStr) {
        if (isEmpty(str)) {
            return "";
        }
        String name = StringUtils.left(str, leftKeepNum);
        return StringUtils.rightPad(name, StringUtils.length(str), padStr);
    }
}
