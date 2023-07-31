package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;

import java.util.Map;

public interface MemberService {

    public void memberSignUp(MemberRequest signupInfo);

    public boolean checkEmail(String email);
}