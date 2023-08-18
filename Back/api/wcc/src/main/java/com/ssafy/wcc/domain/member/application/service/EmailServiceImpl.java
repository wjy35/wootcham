package com.ssafy.wcc.domain.member.application.service;
import com.ssafy.wcc.common.exception.Error;
import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.common.repository.EmailRedisRepository;
import com.ssafy.wcc.domain.member.application.dto.request.EmailVerifyRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;


@PropertySource("classpath:application.properties")
@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailRedisRepository emailRedisRepository;

    @Value("${spring.mail.username}")
    private String id;

    @Override
    // type: 1-회원 가입 시 이메일 인증, 2-비밀번호 찾기
    public String sendMessage(String email, int type) {
        String code = "";
        if (type == 1) code = createCode();
        else if (type == 2) code = createPassword();
        try {
            MimeMessage message = createMessage(email, code, type);
            emailRedisRepository.setDataExpire(code, email, 60 * 5L); // 인증 코드 유효시간: 5분
            javaMailSender.send(message);
        } catch (MailException | MessagingException | UnsupportedEncodingException e) {
            throw new WCCException(Error.EMAIL_SEND_FAILURE);
        }
        return code;
    }

    @Override
    public MimeMessage createMessage(String email, String code, int type) throws UnsupportedEncodingException, MessagingException {
        log.info("이메일 전송 대상: {}", email);

        MimeMessage message = javaMailSender.createMimeMessage();
        // 메일 제목
        if (type == 1) message.setSubject("WCC 회원가입 인증 코드");
        else if (type == 2) message.setSubject("WCC 임시 비밀번호");

        // 메일 내용
        StringBuilder msg = new StringBuilder();
        if (type == 1) msg.append("<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 인증 코드</h1>");
        else if (type == 2) msg.append("<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">임시 비밀번호</h1>");
        msg.append("<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">");
        msg.append(code);
        msg.append("</td></tr></tbody></table></div>");

        message.setText(msg.toString(), "utf-8", "html");
        message.setFrom(new InternetAddress(id,"WootChamClub")); //보내는 사람의 메일 주소, 보내는 사람 이름
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

        return message;
    }

    @Override
    // 6자리 숫자로 된 인증코드 생성
    public String createCode() {
        StringBuffer code = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            code.append((rnd.nextInt(10)));
        }
        return code.toString();
    }

    @Override
    public String createPassword() {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i = 0; i < 16; i++) { // 길이 16의 임시 비밀번호
            idx = sr.nextInt(len);
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }

    @Override
    public boolean verifyEmail(EmailVerifyRequest emailVerifyRequest) throws ChangeSetPersister.NotFoundException {
        String userCode = emailVerifyRequest.getCode();
        String memberEmail = emailRedisRepository.getEmailValue(userCode);

        if (memberEmail == null) throw new ChangeSetPersister.NotFoundException(); // 해당 코드가 저장되지 않은 경우
        emailRedisRepository.deleteEmail(userCode);
        return true;
    }
}
