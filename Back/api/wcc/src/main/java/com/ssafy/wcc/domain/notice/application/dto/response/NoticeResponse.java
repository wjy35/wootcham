package com.ssafy.wcc.domain.notice.application.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class NoticeResponse {
    private String subject;
    private String content;
    private long hit;
    private String date;

    @Builder
    public NoticeResponse(String subject, String content, long hit, Date date) {
        this.subject = subject;
        this.content = content;
        this.hit = hit;
        this.date = changeDateFormat(date);
    }

    private String changeDateFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
