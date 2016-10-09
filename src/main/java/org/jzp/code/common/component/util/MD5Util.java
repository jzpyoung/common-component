package org.jzp.code.common.component.util;

import org.jzp.code.common.component.enums.MD5Enum;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-10-08
 */
public class MD5Util {

    private static MessageDigest md5Digest = null;

    private static final String salt = "qgk8(Y1*Rp";

    static {
        try {
            md5Digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException("MD5 not supported", e);
        }
    }

    public static String generate(String source) {
        return generate(source, "UTF-8", false, MD5Enum.SIXTEEN);
    }

    public static String generate(String source, String charset) {
        return generate(source, charset, false, MD5Enum.SIXTEEN);
    }

    public static String generate(String source, MD5Enum md5Enum) {
        return generate(source, "UTF-8", false, md5Enum);
    }

    public static String generate(String source, boolean withSalt) {
        return generate(source, "UTF-8", withSalt, MD5Enum.SIXTEEN);
    }

    public static String generate(String source, String charset, MD5Enum md5Enum) {
        return generate(source, charset, false, md5Enum);
    }

    public static String generate(String source, String charset, boolean withSalt) {
        return generate(source, charset, withSalt, MD5Enum.SIXTEEN);
    }

    public static String generate(String source, boolean withSalt, MD5Enum md5Enum) {
        return generate(source, "UTF-8", withSalt, md5Enum);
    }

    public static String generate(String source, String charset, boolean withSalt, MD5Enum md5Enum) {
        if (source == null) {
            return null;
        }
        if (withSalt) {
            source = source + salt;
        }
        return digest(source, charset, md5Enum);
    }

    private static String digest(String source, String charset, MD5Enum md5Enum) {
        if (source == null) {
            return null;
        }
        try {
            MessageDigest md5 = (MessageDigest) md5Digest.clone();
            md5.update(source.getBytes(charset));
            byte[] bs = md5.digest();
            return byte2hex(bs, md5Enum);
        } catch (CloneNotSupportedException e) {
            throw new SecurityException("clone of MD5 not supported", e);
        } catch (UnsupportedEncodingException e) {
            throw new SecurityException("encoding " + charset + " not supported", e);
        }
    }

    private static String byte2hex(byte[] bs, MD5Enum md5Enum) {
        String hs = "";
        String stmp;
        for (byte b : bs) {
            stmp = (Integer.toHexString(b & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        switch (md5Enum) {
            case SIXTEEN:
                return hs.substring(8, 24);
            case MITOJI:
                return hs;
            default:
                return hs.substring(8, 24);
        }
    }
}
