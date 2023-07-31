package com.ssafy.wcc.domain.member.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberLoginResponse {
    private Long id;
    private String nickname;
    private int suspension_day;
    private String access_token;
    private String refresh_token;

    @Builder
    public MemberLoginResponse(Long id, String nickname, int suspension_day, String access_token, String refresh_token) {
        this.id = id;
        this.nickname = nickname;
        this.suspension_day = suspension_day;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
}
