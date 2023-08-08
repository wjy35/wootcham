package com.ssafy.wcc.domain.notice.application.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NoticeRequest {

    long id;
    String subject;
    String content;

    @Builder
    public NoticeRequest(long id, String subject, String content) {
        this.id = id;
        this.subject = subject;
        this.content = content;
    }
}
