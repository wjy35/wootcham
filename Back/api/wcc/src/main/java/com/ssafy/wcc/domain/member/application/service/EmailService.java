package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.application.dto.request.EmailVerifyRequest;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    public boolean sendMessage(String email) throws MessagingException, UnsupportedEncodingException;

    public MimeMessage createMessage(String email, String code) throws MessagingException, UnsupportedEncodingException;

    public String createCode();

    public boolean verifyEmail(EmailVerifyRequest emailVerifyRequest) throws ChangeSetPersister.NotFoundException;
}
