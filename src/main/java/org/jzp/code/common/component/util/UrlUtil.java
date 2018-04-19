package org.jzp.code.common.component.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * created by jiazhipeng on 2018/4/19
 */
public class UrlUtil {

    private static final Logger logger = LoggerFactory.getLogger(UrlUtil.class);

    /**
     * 获取网络文件大小
     *
     * @param fileUrl
     * @return
     */
    public static String getFileLength(String fileUrl) {
        String size = null;
        HttpURLConnection urlcon = null;
        try {
            URL url = new URL(fileUrl);
            urlcon = (HttpURLConnection) url.openConnection();
            //根据响应获取文件大小
            int length = urlcon.getContentLength();
            size = getPrintSize(length);
        } catch (MalformedURLException e) {
            logger.info("get url file length fail，url:{};error:{}", fileUrl, e);
        } catch (IOException e) {
            logger.info("get url file length fail，url:{};error:{}", fileUrl, e);
        } catch (Exception e) {
            logger.info("get url file length fail，url:{};error:{}", fileUrl, e);
        } finally {
            if (urlcon != null) {
                urlcon.disconnect();
            }
        }
        return size;
    }

    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        double value = (double) size;
        if (value < 1024) {
            return String.valueOf(value) + "B";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (value < 1024) {
            return String.valueOf(value) + "KB";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        if (value < 1024) {
            return String.valueOf(value) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            return String.valueOf(value) + "GB";
        }
    }
}
