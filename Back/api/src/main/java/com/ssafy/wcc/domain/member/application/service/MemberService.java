package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;

import java.util.Map;

public interface MemberService {

    public void memberSignUp(MemberRequest signupInfo);

    public MemberLoginResponse memberLogin(MemberRequest loginInfo);

    public boolean checkEmail(String email);
}