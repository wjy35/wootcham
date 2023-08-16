package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.application.dto.request.EmailVerifyRequest;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    public String sendMessage(String email, int type);

    public MimeMessage createMessage(String email, String code, int type) throws UnsupportedEncodingException, MessagingException ;

    public String createCode();

    public String createPassword();

    public boolean verifyEmail(EmailVerifyRequest emailVerifyRequest) throws ChangeSetPersister.NotFoundException ;
}
