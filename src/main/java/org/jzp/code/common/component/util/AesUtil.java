package org.jzp.code.common.component.util;

import org.jzp.code.common.component.constant.Constants;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * AES工具类
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-09-27
 */
public class AESUtil {

    // KeyGenerator
    private static KeyGenerator kgen;

    static {
        try {
            kgen = KeyGenerator.getInstance(Constants.AES);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String data, String key) {
        return encrypt(data.getBytes(), key.getBytes());
    }

    public static String decrypt(String decryptStr, String key) {
        return decrypt(decryptStr.getBytes(), key.getBytes());
    }

    public static String encrypt(byte[] data, byte[] key) {
        try {
            SecureRandom random = SecureRandom.getInstance(Constants.SHA1PRNG);
            random.setSeed(key);
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            Cipher cipher = Cipher.getInstance(Constants.CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] result = cipher.doFinal(data);
            return parseByte2HexStr(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(byte[] data, byte[] key) {
        byte[] content;
        try {
            content = parseHexStr2Byte(new String(data));
            SecureRandom random = SecureRandom.getInstance(Constants.SHA1PRNG);
            random.setSeed(key);
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            Cipher cipher = Cipher.getInstance(Constants.CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] doFinal = cipher.doFinal(content);
            return new String(doFinal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
