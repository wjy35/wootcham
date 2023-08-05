package com.ssafy.wcc.domain.member.application.dto.response;

import com.ssafy.wcc.domain.report.db.entity.Report;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberInfoResponse {
    private String email;
    private String nickname;
    private Integer point;
    private Integer money;
    private Integer report;

    @Builder
    public MemberInfoResponse(String email, String nickname, Integer point, Integer money, Integer report) {
        this.email = email;
        this.nickname = nickname;
        this.point = point;
        this.money = money;
        this.report = report;
    }
}
