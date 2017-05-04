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

    /**
     * 判断是否为空字符串
     *
     * @param str 待判断字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || Constants.EMPTY_STR.equals(str)) {
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
        return toBinaryString(l, Constants.BINARY_BITS, Constants.EMPTY_REPLACE_CHAR);
    }

    /**
     * 数字转二进制字符串
     *
     * @param l      待转换数字
     * @param length 转换后的字符串长度
     * @return
     */
    public static String toBinaryString(long l, int length) {
        return toBinaryString(l, length, Constants.EMPTY_REPLACE_CHAR);
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
        return bs.replace(Constants.EMPTY_CHAR, replaceChar);
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
            return Constants.EMPTY_STR;
        }
        String name = StringUtils.right(str, rightKeepNum);
        return StringUtils.leftPad(name, StringUtils.length(str), padStr);
    }

    /**
     * 左部填充固定字符串(和填充长度无关)
     *
     * @param str          待处理字符串
     * @param rightKeepNum 保留右边位数
     * @param padStr       填充字符串
     * @return
     */
    public static String leftPadFixed(String str, int rightKeepNum, String padStr) {
        if (isEmpty(str)) {
            return Constants.EMPTY_STR;
        }

        return padStr.concat(StringUtils.right(str, rightKeepNum));
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
            return Constants.EMPTY_STR;
        }
        int i = 0;
        String removeStr = Constants.EMPTY_STR;
        while (i < leftKeepNum) {
            removeStr += padStr;
            i++;
        }
        return StringUtils.left(str, leftKeepNum)
                .concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(str, rightKeepNum), StringUtils.length(str), padStr), removeStr));
    }

    /**
     * 中部填充固定字符串(和填充长度无关)
     *
     * @param str          待处理字符串
     * @param leftKeepNum  保留左边位数
     * @param rightKeepNum 保留右边位数
     * @param padStr       填充字符串
     * @return
     */
    public static String middlePadFixed(String str, int leftKeepNum, int rightKeepNum, String padStr) {
        if (StringUtils.isBlank(str)) {
            return Constants.EMPTY_STR;
        }

        return StringUtils.left(str, leftKeepNum)
                .concat(padStr)
                .concat(StringUtils.right(str, rightKeepNum));
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
            return Constants.EMPTY_STR;
        }
        String name = StringUtils.left(str, leftKeepNum);
        return StringUtils.rightPad(name, StringUtils.length(str), padStr);
    }

    /**
     * 右部填充固定字符串(和填充长度无关)
     *
     * @param str         待处理字符串
     * @param leftKeepNum 保留左边位数
     * @param padStr      填充字符串
     * @return
     */
    public static String rightPadFixed(String str, int leftKeepNum, String padStr) {
        if (isEmpty(str)) {
            return Constants.EMPTY_STR;
        }
        return StringUtils.left(str, leftKeepNum).concat(padStr);
    }
}
