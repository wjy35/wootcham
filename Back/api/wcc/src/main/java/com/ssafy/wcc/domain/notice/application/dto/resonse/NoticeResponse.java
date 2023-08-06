package com.ssafy.wcc.domain.notice.application.dto.resonse;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
public class NoticeResponse {
    private String subject;
    private String content;
    private int hit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Builder
    public NoticeResponse(String subject, String content, int hit, Date date) {
        this.subject = subject;
        this.content = content;
        this.hit = hit;
        this.date = date;
    }
}
