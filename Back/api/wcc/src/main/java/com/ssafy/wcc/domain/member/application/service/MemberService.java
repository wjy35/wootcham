package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;

public interface MemberService {

    public void memberSignUp(MemberRequest signupInfo);

    public Long memberLogin(MemberRequest loginInfo);

    public void memberDelete(String Id);

    public void memberUpdate(MemberRequest memberRequest, String id);
    public boolean checkEmail(String email);

    public MemberInfoResponse memberInfoResponse(Long id);

    public boolean checkNickname(String nickname);
}