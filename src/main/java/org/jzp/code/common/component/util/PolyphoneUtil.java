package org.jzp.code.common.component.util;

/**
 * 多音字工具类
 * created by jiazhipeng on 2018/3/27
 */
public class PolyphoneUtil {

    public static String getCorrectSpell(char surname) {
        if ('单' == surname) {
            return "shan";
        } else if ('曾' == surname) {
            return "zeng";
        }
        return null;
    }
}
