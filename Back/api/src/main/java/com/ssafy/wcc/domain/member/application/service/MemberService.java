package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;

import java.util.Map;
import java.util.Optional;

public interface MemberService {

    public void memberSignUp(MemberRequest signupInfo);

    public void memberLogin(MemberRequest loginInfo);

    public boolean checkEmail(String email);

    // refreshToken으로 redis에서 email을 꺼내와야함
    // redis namespace 나눈 뒤 구현

    public String getMemberEmail(String refreshToken);

}