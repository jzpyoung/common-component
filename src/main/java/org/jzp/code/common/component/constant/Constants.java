package org.jzp.code.common.component.constant;

/**
 * 常量
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-09-27
 */
public class Constants {

    // 银行卡号最少的位数
    public static final int MIN_CARD_DIGITS = 16;

    // 身份证号最少的位数
    public static final int MIN_ID_CARD_DIGITS = 15;

    // 真实姓名最少的位数
    public static final int MIN_TRUE_NAME_DIGITS = 2;

    // 二进制位数
    public static final int BINARY_BITS = 64;

    // 空字符
    public static final char EMPTY_CHAR = ' ';

    // 空替换字符串
    public static final char EMPTY_REPLACE_CHAR = '0';

    // 邮件分隔符
    public static final char EMAIL_SEPARATOR = '@';

    // AES
    public static final String AES = "AES";

    // SHA1PRNG
    public static final String SHA1PRNG = "SHA1PRNG";

    // CIPHER
    public static final String CIPHER = "AES/ECB/PKCS5Padding";

    // 逗号分隔符
    public static final String SPLIT_STR = ",";

    // 空字符串
    public static final String EMPTY_STR = "";

    // 手机号正则匹配公式
    public static final String PHONE_REGEX = "^1\\d{10}$";

    // *
    public static final String STAR_STR = "*";

    // ***
    public static final String THREE_STAR_STR = "***";

    // ****
    public static final String FOUR_STAR_STR = "****";

    // email
    public static class SEND_EMAIL {
        public static final String HOST = "mail.***.com.cn";
        public static final Integer PORT = 25;
        public static final String USERNAME = "619393827@qq.com";
        public static final String PASSWORD = "******";
        public static final String EMAILFROM = "619393827@qq.com";
        public static final String SYSTEM = "邮件平台";
    }
}
