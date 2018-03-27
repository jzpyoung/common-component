package org.jzp.code.common.component.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音工具类
 * created by jiazhipeng on 2018/3/27
 */
public class PinyinUtil {

    /**
     * 判断一个字符是否是中文字符
     */
    private static boolean isChineseCharacter(char c) {
        return String.valueOf(c).matches("[\\u4E00-\\u9FA5]+");
    }

    /**
     * 将一个含有中文的字符串转换成拼音（多音字支持）第一个字
     */
    public static String transFirstPinyin(String aChineseValue, HanyuPinyinCaseType caseType) {
        if (null == aChineseValue) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char[] charArray = aChineseValue.toCharArray();

        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setCaseType(caseType);
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        String surname;
        if (isChineseCharacter(charArray[0])) {
            try {
                surname = PolyphoneUtil.getCorrectSpell(charArray[0]);
                if (null == surname) {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(charArray[0], outputFormat)[0]);
                } else {
                    sb.append(surname);
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        } else {
            sb.append(charArray[0]);
        }
        return sb.toString();
    }

    /**
     * 将一个含有中文的字符串转换成拼音（多音字支持）所有字
     */
    public static String transPinyin(String aChineseValue, HanyuPinyinCaseType caseType) {
        if (null == aChineseValue) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char[] charArray = aChineseValue.toCharArray();

        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setCaseType(caseType);
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        String surname;
        for (int i = 0; i < charArray.length; i++) {
            if (isChineseCharacter(charArray[i])) {
                try {
                    surname = PolyphoneUtil.getCorrectSpell(charArray[i]);
                    if (null == surname) {
                        sb.append(PinyinHelper.toHanyuPinyinStringArray(charArray[i], outputFormat)[0]);
                    } else {
                        sb.append(surname);
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(charArray[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 取第一个汉字的第一个字符
     */
    public static String getFirstLetter(String ChineseLanguage, HanyuPinyinCaseType caseType) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(caseType);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            String str = String.valueOf(cl_chars[0]);
            if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                hanyupinyin = PinyinHelper.toHanyuPinyinStringArray(
                        cl_chars[0], defaultFormat)[0].substring(0, 1);
                ;
            } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                hanyupinyin += cl_chars[0];
            } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母

                hanyupinyin += cl_chars[0];
                hanyupinyin = hanyupinyin.toUpperCase();
            } else {// 否则不转换

            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin;
    }

    /**
     * 取所有汉字的第一个字符
     */
    public static String getFirstLetters(String ChineseLanguage, HanyuPinyinCaseType caseType) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(caseType);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                String str = String.valueOf(cl_chars[i]);
                if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0].substring(0, 1);
                } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                    hanyupinyin += cl_chars[i];
                } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
                    hanyupinyin += cl_chars[i];
                } else {// 否则不转换
                    hanyupinyin += cl_chars[i];//如果是标点符号的话，带着
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin;
    }
}
