package org.jzp.code.common.component.util;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.jzp.code.common.component.constant.Constants;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

/**
 * 邮件工具类
 * created by jiazhipeng on 2018/3/27
 */
public class EmailUtil {

    private static JavaMailSenderImpl mailSender = createMailSender();

    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(Constants.SEND_EMAIL.HOST);
        sender.setPort(Constants.SEND_EMAIL.PORT);
        sender.setUsername(Constants.SEND_EMAIL.USERNAME);
        sender.setPassword(Constants.SEND_EMAIL.PASSWORD);
        sender.setDefaultEncoding("UTF-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "10000");
        p.setProperty("mail.smtp.auth", "false");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件(文本/html/vm)
     *
     * @param subject 主题
     * @param html    发送内容
     * @param tos     收件人数组
     * @throws MessagingException           异常
     * @throws UnsupportedEncodingException 异常
     */
    public static void sendHtmlMail(String subject, String html, String... tos) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(Constants.SEND_EMAIL.EMAILFROM, Constants.SEND_EMAIL.SYSTEM);
        messageHelper.setTo(tos);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
    }

    /**
     * 发送邮件(附件)
     *
     * @param subject  主题
     * @param text     正文
     * @param file     文件
     * @param fileName 文件名称
     * @param tos      收件人数组
     * @throws MessagingException           异常
     * @throws UnsupportedEncodingException 异常
     */
    public static void sendFileMail(String subject, String text, final File file, String fileName, String... tos) throws Exception {
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //创建MimeMessageHelper对象，处理MimeMessage的辅助类
        // 使用辅助类MimeMessage设定参数
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(Constants.SEND_EMAIL.EMAILFROM, Constants.SEND_EMAIL.SYSTEM);
        messageHelper.setTo(tos);
        messageHelper.setSubject(subject);
        messageHelper.setText(text);
        //加入附件
        messageHelper.addAttachment(fileName, new InputStreamSource() {
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(file);
            }
        });
        // 发送邮件
        mailSender.send(messageHelper.getMimeMessage());
    }
}
