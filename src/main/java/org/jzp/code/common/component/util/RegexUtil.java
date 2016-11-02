package org.jzp.code.common.component.util;

import org.jzp.code.common.component.data.BizCode;
import org.jzp.code.common.component.exception.BizException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-09-27
 */
public class RegexUtil {

    /**
     * 匹配替换
     *
     * @param oldStr   待匹配字符串
     * @param regex    正则匹配字符串
     * @param replaces 替换字符串1,2,3...
     * @return 匹配替换后的字符串
     */
    public static String matchReplace(String oldStr, String regex, String... replaces) {
        if (replaces == null || replaces.length == 0) {
            return oldStr;
        }

        int count = RegexUtil.matchCount(oldStr, regex);
        if (count != replaces.length) {
            throw new BizException(BizCode.BIZ_REGEX_MATCH_NUM_NOT_EQUAL_EXCEPTION);
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(oldStr);
        StringBuffer result = new StringBuffer();
        int i = 0;
        while (matcher.find()) {
            matcher.appendReplacement(result, replaces[i]);
            i++;
        }

        matcher.appendTail(result);
        return result.toString();
    }

    /**
     * 获取匹配个数
     *
     * @param str   待匹配字符串
     * @param regex 正则匹配字符串
     * @return count 匹配个数
     */
    public static int matchCount(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
