package com.ssafy.wcc.domain.notice.application.dto.resonse;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
public class NoticeListResponse {
    private long id;
    private String subject;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Builder
    public NoticeListResponse(long id, String subject, Date date) {
        this.id = id;
        this.subject = subject;
        this.date = date;
    }
}
