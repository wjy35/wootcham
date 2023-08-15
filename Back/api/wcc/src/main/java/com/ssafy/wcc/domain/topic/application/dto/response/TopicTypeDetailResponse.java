package com.ssafy.wcc.domain.topic.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TopicTypeDetailResponse {
    private int type;
    private String name;
    private int subKeyword;

    @Builder
    public TopicTypeDetailResponse(int type, String name, int subKeyword) {
        this.type = type;
        this.name = name;
        this.subKeyword = subKeyword;
    }
}
