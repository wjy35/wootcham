package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;
import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;

import java.util.Map;
import java.util.Optional;

public interface MemberService {

    public void memberSignUp(MemberRequest signupInfo);

    public Long memberLogin(MemberRequest loginInfo);

    public void memberDelete(String Id);

    public boolean checkEmail(String email);

    public MemberInfoResponse memberInfoResponse(Long id);

}