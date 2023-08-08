package com.ssafy.wcc.domain.notice.application.dto.resonse;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class NoticeForUserResponse {
    private String subject;
    private String content;
    private long hit;
    private String date;

    @Builder
    public NoticeForUserResponse(String subject, String content, long hit, Date date) {
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
