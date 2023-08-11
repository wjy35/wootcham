package com.ssafy.wcc.domain.member.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberLoginResponse {
    private String accessToken;
    private String refreshToken;

    @Builder
    public MemberLoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
