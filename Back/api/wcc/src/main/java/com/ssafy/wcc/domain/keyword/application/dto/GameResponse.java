package com.ssafy.wcc.domain.keyword.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GameResponse {
    private int type;
    private String keyword;

    @Builder
    public GameResponse(int type, String keyword) {
        this.type = type;
        this.keyword = keyword;
    }
}
