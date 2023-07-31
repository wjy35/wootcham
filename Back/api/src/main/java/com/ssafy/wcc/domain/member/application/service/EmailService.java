package com.ssafy.wcc.domain.member.application.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    public boolean sendMessage(String email) throws MessagingException, UnsupportedEncodingException;

    public MimeMessage createMessage(String email) throws MessagingException, UnsupportedEncodingException;

    public String createKey();
}
