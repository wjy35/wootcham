package com.ssafy.wcc.domain.report.application.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AllMemberResponse {
    private Long id;
    private String email;
    private String nickname;
    private Integer point;
    private Integer money;
    private Integer admin;
    private LocalDate suspensionDay;
    private Integer reportCount;

    @Builder

    public AllMemberResponse(Long id, String email, String nickname, Integer point, Integer money, Integer admin, LocalDate suspensionDay, Integer reportCount) {
        this.id =id;
        this.email = email;
        this.nickname = nickname;
        this.point = point;
        this.money = money;
        this.admin = admin;
        this.suspensionDay = suspensionDay;
        this.reportCount = reportCount;
    }
}
