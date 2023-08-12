package com.ssafy.wcc.domain.notice.application.dto.resonse;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class NoticeForAdminResponse {
    private long id;
    private String subject;
    private String date;

    @Builder
    public NoticeForAdminResponse(long id, String subject, Date date) {
        this.id = id;
        this.subject = subject;
        this.date = changeDateFormat(date);
    }

    private String changeDateFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
