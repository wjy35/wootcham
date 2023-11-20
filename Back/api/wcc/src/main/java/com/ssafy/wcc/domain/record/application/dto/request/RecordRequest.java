package com.ssafy.wcc.domain.record.application.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RecordRequest {

    long id;
    String nickname;

    @Builder
    public RecordRequest(long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
