package org.jzp.code.common.component.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jzp.code.common.component.constant.Constants;
import org.jzp.code.common.component.data.BizCode;
import org.jzp.code.common.component.exception.BizException;

/**
 * 脱敏工具类
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2017-05-04
 */
public class OcclusionUtil {

    private static String[] fuxingArr = {
            "欧阳", "太史", "端木", "上官", "司马", "东方", "独孤", "南宫", "万俟", "闻人", "夏侯", "诸葛", "尉迟", "公羊",
            "赫连", "澹台", "皇甫", "宗政", "濮阳", "公冶", "太叔", "申屠", "公孙", "慕容", "仲孙", "钟离", "长孙", "宇文",
            "司徒", "鲜于", "司空", "闾丘", "子车", "亓官", "司寇", "巫马", "公西", "颛孙", "壤驷", "公良", "漆雕", "乐正",
            "宰父", "谷梁", "拓跋", "夹谷", "轩辕", "令狐", "段干", "百里", "呼延", "东郭", "南门", "羊舌", "微生", "公户",
            "公玉", "公仪", "梁丘", "公仲", "公上", "公门", "公山", "公坚", "左丘", "公伯", "西门", "公祖", "第五", "公乘",
            "贯丘", "公皙", "南荣", "东里", "东宫", "仲长", "子书", "子桑", "即墨", "达奚", "褚师"};

    /**
     * 对邮箱信息进行脱敏.
     * <p> @前位数>3,前两位 + "***" + 最后一位 + @后面的</p>
     * <br>@前位数<3,第一位 + "***" + @后面的</br>
     *
     * @param email
     * @return input:abc@jzp.com output:a***@jzp.com
     * input:abcd@jzp.com output:ab***d@jzp.com
     */
    public static String occlusionEmail(String email) {
        if (StringUtil.isEmpty(email)) {
            return Constants.EMPTY_STR;
        }

        StringBuilder encryptEmail = new StringBuilder();
        // 根据@符拆分
        String[] StringResult = StringUtils.split(email, Constants.EMAIL_SEPARATOR);
        if (StringResult.length == 2) {
            String emailPrefix = StringResult[0];
            // @符前大于3位
            if (emailPrefix.length() > 3) {
                encryptEmail.append(StringUtil.middlePadFixed(emailPrefix, 2, 1, Constants.THREE_STAR_STR));
            } else {
                encryptEmail.append(StringUtil.rightPadFixed(emailPrefix, 1, Constants.THREE_STAR_STR));
            }
            encryptEmail.append(Constants.EMAIL_SEPARATOR).append(StringResult[1]);
            return encryptEmail.toString();
        } else {
            return Constants.EMPTY_STR;
        }
    }

    /**
     * 对手机号信息进行脱敏.
     * <p> 前3位 + "****" + 后4位.</p>
     * <br>如果输入的不是合法手机号(正则匹配1开头的11位数字), 则返回空字符串</br>
     *
     * @param mobile
     * @return input:18000000000 output:180****0000
     */
    public static String occlusionMobile(String mobile) {
        if (StringUtil.isEmpty(mobile)) {
            return Constants.EMPTY_STR;
        }
        // 校验手机号
        if (!RegexUtil.isMatch(mobile, Constants.PHONE_REGEX)) {
            throw new BizException(BizCode.BIZ_REGEX_PHONE_FORMAT_EXCEPTION);
        }

        return StringUtil.middlePad(mobile, 3, 4, Constants.STAR_STR);
    }

    /**
     * 对身份证进行脱敏.
     * <p>前3位 + "****...." + 后4位</p>
     *
     * @param idCardNo
     * @return input:6217000000000000000 output:621************0000
     */
    public static String occlusionIDCardNo(String idCardNo) {
        if (StringUtil.isEmpty(idCardNo) || idCardNo.length() < Constants.MIN_ID_CARD_DIGITS) {
            return Constants.EMPTY_STR;
        }
        return StringUtil.middlePad(idCardNo, 3, 4, Constants.STAR_STR);
    }

    /**
     * 对银行卡进行脱敏.
     * <p>"****" + 后四位</p>
     *
     * @param cardNo
     * @return input:6225882133271010 output:****1010
     */
    public static String occlusionCardNo(String cardNo) {
        if (StringUtil.isEmpty(cardNo) || cardNo.length() < Constants.MIN_CARD_DIGITS) {
            return Constants.EMPTY_STR;
        }
        return StringUtil.leftPadFixed(cardNo, 4, Constants.FOUR_STAR_STR);
    }

    /**
     * 对真实姓名进行脱敏.
     * <p> 隐藏掉姓 + 剩余位数</p>
     * <br>需要处理复姓问题(现存的复姓有81个)</br>
     *
     * @param trueName
     * @return input:贾艾嘉 output:*艾嘉
     */
    public static String occlusionTrueName(String trueName) {
        if (StringUtil.isEmpty(trueName)) {
            return Constants.EMPTY_STR;
        }

        int length = trueName.length();
        if (length < Constants.MIN_TRUE_NAME_DIGITS) {
            return Constants.EMPTY_STR;
        }
        StringBuilder occlusionName = new StringBuilder();

        if (length > Constants.MIN_TRUE_NAME_DIGITS) {
            String fuxingStr = trueName.substring(0, Constants.MIN_TRUE_NAME_DIGITS);
            if (ArrayUtils.contains(fuxingArr, fuxingStr)) {
                occlusionName.append(Constants.STAR_STR).append(trueName.substring(Constants.MIN_TRUE_NAME_DIGITS));
            } else {
                occlusionName.append(Constants.STAR_STR).append(trueName.substring(Constants.MIN_TRUE_NAME_DIGITS - 1));
            }
        } else {
            occlusionName.append(Constants.STAR_STR).append(trueName.substring(Constants.MIN_TRUE_NAME_DIGITS - 1));
        }

        return occlusionName.toString();
    }
}
