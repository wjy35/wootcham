package com.ssafy.wcc.domain.member.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("profile_img")
    private String profileImg;
    private String badge;
    private String border;
    private Long ranking; //순위
    private Long top; // 상위 %

    @Builder
    public MemberInfoResponse(String email, String nickname, Integer point, Integer money, String profileImg, String badge, String border, Long ranking, Long top) {
        this.email = email;
        this.nickname = nickname;
        this.point = point;
        this.money = money;
        this.profileImg = profileImg;
        this.badge = badge;
        this.border = border;
        this.ranking = ranking;
        this.top = top;
    }
}
