package com.ssafy.wcc.domain.member.application.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.IllegalWriteException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;


@PropertySource("classpath:application.properties")
@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String id;

    @Override
    public boolean sendMessage(String email) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = createMessage(email);
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        return true;
    }

    @Override
    public MimeMessage createMessage(String email) throws UnsupportedEncodingException, MessagingException {
        log.info("이메일 전송 대상: {}", email);

        MimeMessage message = javaMailSender.createMimeMessage();
        message.setSubject("WCC 회원가입 인증 코드"); // 메일 제목

        // 메일 내용
        StringBuilder msg = new StringBuilder();
        msg.append("<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 인증 코드</h1>");
        msg.append("<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">");
        msg.append(createKey());
        msg.append("</td></tr></tbody></table></div>");

        message.setText(msg.toString(), "utf-8", "html");
        message.setFrom(new InternetAddress(id,"WootChamClub")); //보내는 사람의 메일 주소, 보내는 사람 이름
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

        return message;
    }

    @Override
    // 6자리 숫자로 된 인증코드 생성
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }
}
