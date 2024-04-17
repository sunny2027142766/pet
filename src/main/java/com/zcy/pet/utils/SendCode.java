package com.zcy.pet.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendCode {

    private final JavaMailSenderImpl mailSender;
    private final RedisUtil redisUtil;

    @Value("${email.from}")
    public String from;

    @Value("${email.time}")
    public String time;

    @Value("${email.subject}")
    public String subject;

    public String sendCode(String email) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //生成随机验证码
        String code = CodeUtil.generateCode(6);
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //设置一个html邮件信息
        helper.setText("<p style='color: blue'>用户您好！你的验证码为：" + code + "(有效期为5分钟)</p>", true);
        //设置邮件主题名
        helper.setSubject(subject);
        //发给谁-》邮箱地址
        helper.setTo(email);
        //谁发的-》发送人邮箱
        helper.setFrom(from);
        //将邮箱验证码以邮件地址为key存入redis,1分钟过期
        redisUtil.set(email, code, Long.valueOf(time));
        // 发送邮件
        mailSender.send(mimeMessage);
        return code;
    }
}
