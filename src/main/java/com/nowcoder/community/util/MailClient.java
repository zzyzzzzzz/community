package com.nowcoder.community.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailClient {
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class); //要记录日志的所以先声明一个logger
    //核心组件
    @Autowired
    private JavaMailSender mailSender;

    //发邮件需要的条件 发件者 收件者 标题 内容
    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String to,String subject, String content){
        //用组件发邮件 Ctrl点进去看看
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);//表示支持发送html文件
            mailSender.send(helper.getMimeMessage());

        }catch (MessagingException e){
            logger.error("Send Failed:" + e.getMessage());
        }



    }


}
