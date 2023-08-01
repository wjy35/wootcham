package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;

import java.util.Map;

public interface TokenService {
    public String createAccessToken(String email);
    public String createRefreshToken(String id);

    public String create(String id, String subject, long expireMin);

    public byte[] generateKey();

    public Map<String, Object> checkAndGetClaims(String jwt);

    public boolean checkToken(String jwt);

    public MemberLoginResponse makeMemberLoginResponse(String email);
}
