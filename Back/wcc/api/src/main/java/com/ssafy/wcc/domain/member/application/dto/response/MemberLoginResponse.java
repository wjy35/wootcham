package com.ssafy.wcc.domain.member.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberLoginResponse {
    private String access_token;
    private String refresh_token;

    @Builder
    public MemberLoginResponse(String access_token, String refresh_token) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
}
