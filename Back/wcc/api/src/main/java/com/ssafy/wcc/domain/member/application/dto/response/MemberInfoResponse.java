package com.ssafy.wcc.domain.member.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfoResponse {
    private String email;
    private String nickname;
    private Integer point;
    private Integer money;
    private Integer report;

    @Builder
    public MemberInfoResponse(String email, String nickname, int point, int money, int report) {
        this.email = email;
        this.nickname = nickname;
        this.point = point;
        this.money = money;
        this.report = report;
    }
}
