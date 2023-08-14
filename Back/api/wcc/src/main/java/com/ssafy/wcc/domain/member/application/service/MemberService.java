package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.request.MemberloginRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;
import com.ssafy.wcc.domain.member.db.entity.Member;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Optional;

public interface MemberService {

    public void memberSignUp(MemberRequest signupInfo);

    public Member memberLogin(MemberloginRequest loginInfo);

    public void memberDelete(String Id);

    public void memberUpdate(MemberRequest memberRequest, String id);
    public void checkEmail(String email);

    public MemberInfoResponse memberInfoResponse(Long id);

    public boolean checkNickname(String nickname);

    public String getMemberNickname(Long id);

    public LocalDate getCurrentTime();

    public void updateCurrentLogin(Optional<Member> findMember);

    public void checkSuspendedMember(Optional<Member> findMember);

    public void setTmpPassword(String email);
}