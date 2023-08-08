package com.ssafy.wcc.domain.report.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AllMemberResponse {
    private String email;
    private String nickname;
    private Integer point;
    private Integer money;
    private Integer admin;
    private Integer suspensionDay;
    private Integer reportCount;

    @Builder

    public AllMemberResponse(String email, String nickname, Integer point, Integer money, Integer admin, Integer suspensionDay, Integer reportCount) {
        this.email = email;
        this.nickname = nickname;
        this.point = point;
        this.money = money;
        this.admin = admin;
        this.suspensionDay = suspensionDay;
        this.reportCount = reportCount;
    }
}
